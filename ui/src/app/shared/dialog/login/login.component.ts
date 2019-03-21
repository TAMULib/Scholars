import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { Store, select } from '@ngrx/store';

import { combineLatest, of, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { AppState } from '../../../core/store';
import { DialogButtonType, DialogControl } from '../../../core/model/dialog';

import { selectIsLoggingIn } from '../../../core/store/auth';

import * as fromAuth from '../../../core/store/auth/auth.actions';
import * as fromDialog from '../../../core/store/dialog/dialog.actions';

@Component({
    selector: 'scholars-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    public dialog: DialogControl;

    constructor(
        private builder: FormBuilder,
        private translate: TranslateService,
        private store: Store<AppState>
    ) {

    }

    ngOnInit() {
        this.dialog = {
            title: this.translate.get('SHARED.DIALOG.LOGIN.TITLE'),
            form: this.builder.group({
                email: new FormControl('', [
                    Validators.required,
                    Validators.email
                ]),
                password: new FormControl('', [
                    Validators.required
                ])
            }),
            close: {
                type: DialogButtonType.OUTLINE_WARNING,
                label: this.translate.get('SHARED.DIALOG.LOGIN.CANCEL'),
                action: () => this.store.dispatch(new fromDialog.CloseDialogAction()),
                disabled: () => this.store.pipe(select(selectIsLoggingIn))
            },
            submit: {
                type: DialogButtonType.OUTLINE_PRIMARY,
                label: this.translate.get('SHARED.DIALOG.LOGIN.SUBMIT'),
                action: () => this.store.dispatch(new fromAuth.LoginAction({ login: this.dialog.form.value })),
                disabled: () => combineLatest(
                    of(this.dialog.form.invalid),
                    of(this.dialog.form.pristine),
                    this.store.pipe(select(selectIsLoggingIn))
                ).pipe(map(results => results[0] || results[1] || results[2]))
            }
        };
    }

    public isValid(field: string): boolean {
        const formControl = this.dialog.form.controls[field];
        return formControl.touched && formControl.valid;
    }

    public isInvalid(field: string): boolean {
        const formControl = this.dialog.form.controls[field];
        return formControl.dirty && formControl.invalid;
    }

    public getErrorMessage(field: string): Observable<string> {
        const errors = this.dialog.form.controls[field].errors;
        for (const validation in errors) {
            if (errors.hasOwnProperty(validation)) {
                switch (validation) {
                    case 'required': return this.translate.get('SHARED.DIALOG.VALIDATION.REQUIRED', { field });
                    case 'email': return this.translate.get('SHARED.DIALOG.VALIDATION.EMAIL', { field });
                    default: return of('unknown error');
                }
            }
        }
    }

}
