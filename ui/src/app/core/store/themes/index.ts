import {
    createSelector,
    createFeatureSelector
} from '@ngrx/store';

import { Theme, Style } from '../../model/theme';

import * as fromThemes from './themes.reducer';

export const selectThemesState = createFeatureSelector<fromThemes.ThemesState>('themes');

export const selectThemesIds = createSelector(selectThemesState, fromThemes.selectThemesIds);
export const selectThemesEntities = createSelector(selectThemesState, fromThemes.selectThemesEntities);
export const selectAllThemes = createSelector(selectThemesState, fromThemes.selectAllThemes);
export const selectThemesTotal = createSelector(selectThemesState, fromThemes.selectThemesTotal);
export const selectThemesError = createSelector(selectThemesState, fromThemes.getThemesError);
export const selectThemesIsLoading = createSelector(selectThemesState, fromThemes.isThemesLoading);

export const selectThemesPage = createSelector(selectThemesState, fromThemes.getThemesPage);
export const selectThemesLinks = createSelector(selectThemesState, fromThemes.getThemesLinks);

export const selectThemesByName = (name: string) => createSelector(selectAllThemes, (themes: Theme[]) => {
    return themes ? themes.find(theme => theme.name === name) : undefined;
});

export const selectStyle = createSelector(selectThemesState, fromThemes.getStyle);

export const selectIsLoadingActiveTheme = createSelector(selectThemesState, fromThemes.isLoadingActiveTheme);
export const getActiveTheme = createSelector(selectThemesState, fromThemes.getActiveTheme);

export const selectActiveThemeOrganization = createSelector(getActiveTheme, (theme: Theme) => theme ? theme.organization : undefined);

export const selectActiveThemeColors = createSelector(getActiveTheme, (theme: Theme) => theme ? theme.colors : []);
export const selectActiveThemeVariants = createSelector(getActiveTheme, (theme: Theme) => theme ? theme.variants : []);
export const selectActiveThemeVariables = createSelector(getActiveTheme, (theme: Theme) => theme ? theme.variables : []);

export const selectActiveThemeHome = createSelector(getActiveTheme, (theme: Theme) => theme ? theme.home : undefined);

export const selectActiveThemeHeader = createSelector(getActiveTheme, (theme: Theme) => theme ? theme.header : undefined);
export const selectActiveThemeHeaderNavbar = createSelector(getActiveTheme, (theme: Theme) => theme && theme.header ? theme.header.navbar : undefined);
export const selectActiveThemeHeaderBanner = createSelector(getActiveTheme, (theme: Theme) => theme && theme.header ? theme.header.banner : undefined);

export const selectActiveThemeFooter = createSelector(getActiveTheme, (theme: Theme) => theme ? theme.footer : undefined);

const selectStyleByKey = (styles: Style[], key: string) => {
    for (const style of styles) {
        if (style.key === key) {
            return style.value;
        }
    }
    return undefined;
};

export const selectActiveThemeColor = (key: string) => createSelector(getActiveTheme, (theme: Theme) => selectStyleByKey((theme ? theme.colors : []), key));
export const selectActiveThemeVariant = (key: string) => createSelector(getActiveTheme, (theme: Theme) => selectStyleByKey((theme ? theme.variants : []), key));
export const selectActiveThemeVariable = (key: string) => createSelector(getActiveTheme, (theme: Theme) => selectStyleByKey((theme ? theme.variables : []), key));
