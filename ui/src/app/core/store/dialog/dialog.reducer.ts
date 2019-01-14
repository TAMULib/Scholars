import { DialogActions, DialogActionTypes } from './dialog.actions';

export type DialogState = Readonly<{
    opened: boolean;
    opening: boolean;
    closing: boolean;
}>;

export const initialState: DialogState = {
    opened: false,
    opening: false,
    closing: false
};

export function reducer(state = initialState, action: DialogActions): DialogState {
    switch (action.type) {
        case DialogActionTypes.OPEN_DIALOG:
            return {
                ...state,
                opening: true
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
                closing: false
            };
        default:
            return state;
    }
}

export const isDialogOpened = (state: DialogState) => state.opened;
export const isDialogOpening = (state: DialogState) => state.opening;
export const isDialogClosed = (state: DialogState) => !state.opened;
export const isDialogClosing = (state: DialogState) => state.closing;

