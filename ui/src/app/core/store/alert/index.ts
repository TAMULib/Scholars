import { createSelector, createFeatureSelector } from '@ngrx/store';

import { Alert, AlertLocation } from './';

import * as fromAlert from './alert.reducer';

export const selectAlertState = createFeatureSelector<fromAlert.AlertState>('alert');

export const selectAlerts = createSelector(selectAlertState, fromAlert.getAlerts);

export const selectAlertsByLocation = (location: AlertLocation) => createSelector(selectAlerts, (alerts: Map<AlertLocation, Alert[]>) => {
    return alerts.get(location);
});

export { AlertLocation, AlertType, Alert } from './alert.model';
