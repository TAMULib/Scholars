import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';

import { of } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';

import { UsersService } from '../../service/users.service';

import { AlertLocation, AlertType } from '../alert/alert.model';
import { SdrCollection } from '../../model/sdr';
import { SdrPageRequest } from '../../model/sdr/sdr-page';
import { User } from '../../model/user';

import * as fromAlerts from '../alert/alert.actions';
import * as fromAuth from '../auth/auth.actions';
import * as fromDialog from '../dialog/dialog.actions';
import * as fromUsers from './users.actions';

@Injectable()
export class UsersEffects {

    constructor(private actions: Actions, private usersService: UsersService) {

    }

    @Effect() loadUsers = this.actions.pipe(
        ofType(fromUsers.UsersActionTypes.LOAD),
        map((action: fromUsers.LoadUsersAction) => action.payload),
        map((payload: { page: SdrPageRequest }) => payload.page),
        switchMap((page: SdrPageRequest) =>
            this.usersService.getPage(page).pipe(
                map((collection: SdrCollection) => new fromUsers.LoadUsersSuccessAction({ collection })),
                catchError((response) => of(new fromUsers.LoadUsersFailureAction({ response })))
            )
        )
    );

    @Effect() loadUsersFailure = this.actions.pipe(
        ofType(fromUsers.UsersActionTypes.LOAD_FAILURE),
        map((action: fromUsers.LoadUsersFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.MAIN,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() updateUser = this.actions.pipe(
        ofType(fromUsers.UsersActionTypes.UPDATE),
        map((action: fromUsers.UpdateUserAction) => action.payload),
        switchMap((payload: { user: User }) =>
            this.usersService.patchUser(payload.user).pipe(
                map((user: User) => new fromUsers.UpdateUserSuccessAction({ user })),
                catchError((response) => of(new fromUsers.UpdateUserFailureAction({ response })))
            )
        )
    );

    @Effect() updateUserSuccess = this.actions.pipe(
        ofType(fromUsers.UsersActionTypes.UPDATE_SUCCESS),
        map((action: fromUsers.UpdateUserSuccessAction) => action.payload),
        map((payload: { user: User }) => payload.user),
        switchMap((user: User) => [
            new fromDialog.CloseDialogAction(),
            new fromAlerts.AddAlertAction({
                alert: {
                    location: AlertLocation.MAIN,
                    type: AlertType.SUCCESS,
                    message: 'Successfully update user',
                    dismissible: true,
                    timer: 10000
                }
            })
        ])
    );

    @Effect() updateUserFailure = this.actions.pipe(
        ofType(fromUsers.UsersActionTypes.UPDATE_FAILURE),
        map((action: fromUsers.UpdateUserFailureAction) => action.payload),
        map((payload: { response: any }) => new fromAlerts.AddAlertAction({
            alert: {
                location: AlertLocation.DIALOG,
                type: AlertType.DANGER,
                message: payload.response.error,
                dismissible: true,
                timer: 15000
            }
        }))
    );

    @Effect() clearUsers = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.LOGOUT_SUCCESS),
        map(() => new fromUsers.ClearUsersAction())
    );

}
