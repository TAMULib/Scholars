import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import { Theme, Style } from '../../model/theme';

import * as fromTheme from './theme.reducer';

export const selectThemeState = createFeatureSelector<fromTheme.ThemeState>('theme');

export const selectStyle = createSelector(selectThemeState, fromTheme.getStyle);
export const selectError = createSelector(selectThemeState, fromTheme.getActiveTheme);
export const selectIsLoadingActiveTheme = createSelector(selectThemeState, fromTheme.isLoadingActiveTheme);
export const selectActiveTheme = createSelector(selectThemeState, fromTheme.getActiveTheme);

export const selectActiveThemeOrganization = createSelector(selectActiveTheme, (theme: Theme) => theme ? theme.organization : undefined);

export const selectActiveThemeColors = createSelector(selectActiveTheme, (theme: Theme) => theme ? theme.colors : []);
export const selectActiveThemeVariants = createSelector(selectActiveTheme, (theme: Theme) => theme ? theme.variants : []);
export const selectActiveThemeVariables = createSelector(selectActiveTheme, (theme: Theme) => theme ? theme.variables : []);

export const selectActiveThemeHome = createSelector(selectActiveTheme, (theme: Theme) => theme ? theme.home : undefined);

export const selectActiveThemeHeader = createSelector(selectActiveTheme, (theme: Theme) => theme ? theme.header : undefined);
export const selectActiveThemeHeaderNavbar = createSelector(selectActiveTheme, (theme: Theme) => theme && theme.header ? theme.header.navbar : undefined);
export const selectActiveThemeHeaderBanner = createSelector(selectActiveTheme, (theme: Theme) => theme && theme.header ? theme.header.banner : undefined);

export const selectActiveThemeFooter = createSelector(selectActiveTheme, (theme: Theme) => theme ? theme.footer : undefined);

const selectStyleByKey = (styles: Style[], key: string) => {
    for (const style of styles) {
        if (style.key === key) {
            return style.value;
        }
    }
    return undefined;
};

export const selectActiveThemeColor = (key: string) => createSelector(selectActiveTheme, (theme: Theme) => selectStyleByKey((theme ? theme.colors : []), key));
export const selectActiveThemeVariant = (key: string) => createSelector(selectActiveTheme, (theme: Theme) => selectStyleByKey((theme ? theme.variants : []), key));
export const selectActiveThemeVariable = (key: string) => createSelector(selectActiveTheme, (theme: Theme) => selectStyleByKey((theme ? theme.variables : []), key));
