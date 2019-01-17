import { AlertActions, AlertActionTypes } from './alert.actions';
import { Alert, AlertLocation } from './';

export interface AlertState {
    alerts: Map<AlertLocation, Alert[]>;
}

export const initialState: AlertState = {
    alerts: new Map<AlertLocation, Alert[]>([
        [AlertLocation.MAIN, []],
        [AlertLocation.DIALOG, []]
    ])
};

export function reducer(state = initialState, action: AlertActions): AlertState {
    switch (action.type) {
        case AlertActionTypes.ADD_ALERT: {
            const alert = action.payload.alert;
            const alerts = state.alerts.get(alert.location);
            const existingAlerts = alerts.filter(currentAlert => currentAlert.type === alert.type && currentAlert.message === alert.message);
            if (existingAlerts.length === 0) {
                alerts.push(Object.assign(alert, { index: alerts.length }));
            }
            return {
                ...state
            };
        }
        case AlertActionTypes.DISMISS_ALERT: {
            const alert = action.payload.alert;
            const alerts = state.alerts.get(alert.location);
            for (let i = alerts.length - 1; i > action.payload.alert.index; i--) {
                alerts[i] = Object.assign(alerts[i], { index: alerts[i].index - 1 });
            }
            alerts.splice(action.payload.alert.index, 1);
            return {
                ...state
            };
        }
        default:
            return state;
    }
}

export const getAlerts = (state: AlertState) => state.alerts;
