import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';

import { of } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';

import { UsersService } from '../../service/users.service';

import { AlertLocation, AlertType } from '../alert/alert.model';
import { SdrCollection } from '../../model/sdr';
import { SdrPageRequest } from '../../model/sdr/sdr-page';

import * as fromUsers from './users.actions';
import * as fromAlerts from '../alert/alert.actions';
import * as fromAuth from '../auth/auth.actions';

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
        map((payload: { response: any }) => new fromAlerts.AddMainErrorAlertAction(payload))
    );

    @Effect() clearUsers = this.actions.pipe(
        ofType(fromAuth.AuthActionTypes.LOGOUT_SUCCESS),
        map(() => new fromUsers.ClearUsersAction())
    );

}
