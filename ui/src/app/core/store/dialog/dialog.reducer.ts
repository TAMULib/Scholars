import { DialogActions, DialogActionTypes } from './dialog.actions';
import { Dialog } from '../../model/dialog';

export type DialogState = Readonly<{
    opened: boolean;
    opening: boolean;
    closing: boolean;
    dialog: Dialog;
}>;

export const initialState: DialogState = {
    opened: false,
    opening: false,
    closing: false,
    dialog: undefined
};

export function reducer(state = initialState, action: DialogActions): DialogState {
    switch (action.type) {
        case DialogActionTypes.OPEN_DIALOG:
            return {
                ...state,
                opening: true,
                dialog: action.payload.dialog
            };
        case DialogActionTypes.DIALOG_OPENED:
            return {
                ...state,
                opened: true,
                opening: false
            };
        case DialogActionTypes.CLOSE_DIALOG:
            return {
                ...state,
                closing: true
            };
        case DialogActionTypes.DIALOG_CLOSED:
            return {
                ...state,
                opened: false,
                closing: false,
                dialog: undefined
            };
        default:
            return state;
    }
}

export const isDialogOpened = (state: DialogState) => state.opened;
export const isDialogOpening = (state: DialogState) => state.opening;
export const isDialogClosed = (state: DialogState) => !state.opened;
export const isDialogClosing = (state: DialogState) => state.closing;
export const getDialog = (state: DialogState) => state.dialog;
