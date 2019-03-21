import { createSelector, createFeatureSelector } from '@ngrx/store';

import * as fromDialog from './dialog.reducer';

export const selectDialogState = createFeatureSelector<fromDialog.DialogState>('dialog');

export const selectDialogIsOpened = createSelector(selectDialogState, fromDialog.isDialogOpened);
export const selectDialogIsOpening = createSelector(selectDialogState, fromDialog.isDialogOpening);
export const selectDialogIsClosed = createSelector(selectDialogState, fromDialog.isDialogClosed);
export const selectDialogIsClosing = createSelector(selectDialogState, fromDialog.isDialogClosing);
export const selectDialog = createSelector(selectDialogState, fromDialog.getDialog);
