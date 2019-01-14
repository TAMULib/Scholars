import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { DOCUMENT, isPlatformServer, isPlatformBrowser } from '@angular/common';
import { DomSanitizer, SafeStyle } from '@angular/platform-browser';

import { Observable, of } from 'rxjs';

import { RestService } from './rest.service';

import { SdrCollection, SdrPageRequest } from '../model/sdr';
import { Theme, Style } from '../model/theme';

import { hexToRgb, luminance, mix, yiq } from '../../shared/utilities/color.utility';

import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class ThemesService {

    constructor(
        @Inject(PLATFORM_ID) private platformId: string,
        @Inject(DOCUMENT) private document: Document,
        private sanitizer: DomSanitizer,
        private restService: RestService
    ) {

    }

    public getPage(page: SdrPageRequest): Observable<SdrCollection> {
        return this.restService.get<SdrCollection>(environment.service + `/themes?page=${page.number - 1}&size=${page.size}`, {
            withCredentials: true
        });
    }

    public getActiveTheme(): Observable<Theme> {
        return this.restService.get<Theme>(environment.service + '/themes/search/active');
    }

    public applyActiveTheme(theme: Theme): Observable<SafeStyle> {
        let styles = '';
        styles += this.processThemeColors(theme.colors);
        styles += this.processThemeVariants(theme.variants);
        styles += this.processThemeVariables(theme);
        return of(this.sanitizer.bypassSecurityTrustStyle(styles));
    }

    private processThemeColors(colors: Style[]): string {
        let styles = '';
        // update color variables
        for (const color of colors) {
            styles += `${color.key}: ${color.value};`;
        }
        return styles;
    }

    private processThemeVariants(variants: Style[]): string {
        let computedStyle;

        if (isPlatformServer(this.platformId)) {
            computedStyle = this.getPrerenderComputedStyle(this.document);
        }

        if (isPlatformBrowser(this.platformId)) {
            computedStyle = getComputedStyle(this.document.body);
        }

        const yiqContrastedThreshold = Number(computedStyle.getPropertyValue('--yiq-contrasted-threshold').trim());
        const yiqTextDark = computedStyle.getPropertyValue('--yiq-text-dark').trim();
        const yiqTextLight = computedStyle.getPropertyValue('--yiq-text-light').trim();

        const themeColorInterval = parseInt(computedStyle.getPropertyValue('--theme-color-interval').trim(), 10);

        const alertBackgroundLevel = Number(computedStyle.getPropertyValue('--alert-bg-level').trim());
        const alertBorderLevel = Number(computedStyle.getPropertyValue('--alert-border-level').trim());
        const alertColorLevel = Number(computedStyle.getPropertyValue('--alert-color-level').trim());

        const listGroupItemBackgroundLevel = Number(computedStyle.getPropertyValue('--list-group-item-bg-level').trim());
        const listGroupItemColorLevel = Number(computedStyle.getPropertyValue('--list-group-item-color-level').trim());

        const tableBackgroundLevel = Number(computedStyle.getPropertyValue('--table-bg-level').trim());
        const tableBorderLevel = Number(computedStyle.getPropertyValue('--table-border-level').trim());

        const black = computedStyle.getPropertyValue('--black').trim();
        const white = computedStyle.getPropertyValue('--white').trim();

        const constrast = (level: number) => level > 0 ? black : white;
        const yiqConstrast = (value: number) => value >= yiqContrastedThreshold ? yiqTextDark : yiqTextLight;

        let styles = '';

        // update variant variables
        for (const variant of variants) {

            const key = variant.key;
            const value = variant.value;

            // update theme variable
            styles += `${key}: ${value};`;

            // update alert varients
            const alertBgValue = mix(constrast(alertBackgroundLevel), value, Math.abs(alertBackgroundLevel) * themeColorInterval);
            styles += `${key}-alert-bg: ${alertBgValue};`;

            const alertBorderValue = mix(constrast(alertBorderLevel), value, Math.abs(alertBorderLevel) * themeColorInterval);
            styles += `${key}-alert-border: ${alertBorderValue};`;

            const alertColorValue = mix(constrast(alertColorLevel), value, Math.abs(alertColorLevel) * themeColorInterval);
            styles += `${key}-alert-color: ${alertColorValue};`;


            // update badge varients
            const badgeBgValue = value;
            styles += `${key}-badge-bg: ${badgeBgValue};`;

            const badgeColorValue = yiqConstrast(yiq(value));
            styles += `${key}-badge-color: ${badgeColorValue};`;


            // update list item group varients
            const listGroupItemBgValue = mix(constrast(listGroupItemBackgroundLevel), value, Math.abs(listGroupItemBackgroundLevel) * themeColorInterval);
            styles += `${key}-list-group-item-bg: ${listGroupItemBgValue};`;

            const listGroupItemColorValue = mix(constrast(listGroupItemColorLevel), value, Math.abs(listGroupItemColorLevel) * themeColorInterval);
            styles += `${key}-list-group-item-color: ${listGroupItemColorValue};`;


            // update table varients
            const tableBgValue = mix(constrast(tableBackgroundLevel), value, Math.abs(tableBackgroundLevel) * themeColorInterval);
            styles += `${key}-table-bg: ${tableBgValue};`;

            const tableBorderValue = mix(constrast(tableBorderLevel), value, Math.abs(tableBorderLevel) * themeColorInterval);
            styles += `${key}-table-border: ${tableBorderValue};`;


            // update button outline varients
            const buttonOutlineColorValue = value;
            styles += `${key}-button-outline-color: ${buttonOutlineColorValue};`;

            const buttonOutlineColorHoverValue = yiqConstrast(yiq(value));
            styles += `${key}-button-outline-color-hover: ${buttonOutlineColorHoverValue};`;

            const bobsrgba = hexToRgb(value);
            const buttonOutlineBoxShadowColorValue = `rgba(${bobsrgba.r}, ${bobsrgba.g}, ${bobsrgba.b}, .5)`;
            styles += `${key}-button-outline-box-shadow-color: ${buttonOutlineBoxShadowColorValue};`;


            // update button varients
            const buttonColorValue = yiqConstrast(yiq(value));
            styles += `${key}-button-color: ${buttonColorValue};`;

            const buttonBgValue = value;
            styles += `${key}-button-bg: ${buttonBgValue};`;

            const buttonBorderValue = value;
            styles += `${key}-button-border: ${buttonBorderValue};`;

            const buttonHoverColorValue = yiqConstrast(yiq(luminance(value, -0.1165)));
            styles += `${key}-button-hover-color: ${buttonHoverColorValue};`;

            const buttonHoverBgValue = luminance(value, -0.1165);
            styles += `${key}-button-hover-bg: ${buttonHoverBgValue};`;

            const buttonHoverBorderValue = luminance(value, -0.1415);
            styles += `${key}-button-hover-border: ${buttonHoverBorderValue};`;

            const buttonActiveColorValue = yiqConstrast(yiq(luminance(value, -0.1415)));
            styles += `${key}-button-active-color: ${buttonActiveColorValue};`;

            const buttonActiveBgValue = luminance(value, -0.1415);
            styles += `${key}-button-active-bg: ${buttonActiveBgValue};`;

            const buttonActiveBorderValue = luminance(value, -0.17);
            styles += `${key}-button-active-border: ${buttonActiveBorderValue};`;

            const bbsrgba = hexToRgb(mix(yiqConstrast(yiq(luminance(buttonBgValue, -0.1165))), buttonBorderValue, 15));
            const buttonBoxShadowColorValue = `rgba(${bbsrgba.r}, ${bbsrgba.g}, ${bbsrgba.b}, .5)`;
            styles += `${key}-button-box-shadow-color: ${buttonBoxShadowColorValue};`;
        }
        return styles;
    }

    private processThemeVariables(theme: Theme): string {
        return this.processVariables(theme);
    }

    private processVariables(component: any): string {
        let styles = '';
        if (component) {
            Object.keys(component).forEach((key) => {
                const value = component[key];
                if (key === 'variables') {
                    for (const variable of value) {
                        styles += `${variable.key}: ${variable.value};`;
                    }
                } else {
                    if (typeof value === 'object') {
                        styles += this.processVariables(value);
                    }
                }
            });
        }
        return styles;
    }

    private getPrerenderComputedStyle(document: Document): any {
        const head = (document.head as any).serialize();
        const root = head.match(/:root {([^}]+)}/g)[0];
        const cssTxt = root.replace(/\/\*(.|\s)*?\*\//g, ' ').replace(/\s+/g, ' ');
        const style = {}, [, ruleName, rule] = cssTxt.match(/ ?(.*?) ?{([^}]*)}/) || [, , cssTxt];
        const properties = rule.split(';').map(o => o.split(':').map(x => x && x.trim()));
        for (const [property, value] of properties) { if (value) { style[property] = value; } }
        return { root, ruleName, style, getPropertyValue: (key) => style[key] };
    }

}
