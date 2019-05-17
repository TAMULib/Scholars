import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { Store, select } from '@ngrx/store';

import { combineLatest, scheduled, Observable } from 'rxjs';
import { queue } from 'rxjs/internal/scheduler/queue';
import { map } from 'rxjs/operators';

import { AppState } from '../../../core/store';
import { DialogButtonType, DialogControl } from '../../../core/model/dialog';
import { Role, User } from '../../../core/model/user';

import { selectResourceIsUpdating } from '../../../core/store/sdr';

import * as fromDialog from '../../../core/store/dialog/dialog.actions';
import * as fromSdr from '../../../core/store/sdr/sdr.actions';

@Component({
    selector: 'scholars-user-edit',
    templateUrl: './user-edit.component.html',
    styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {

    @Input() user: User;

    public roles: string[];

    public dialog: DialogControl;

    constructor(
        private builder: FormBuilder,
        private translate: TranslateService,
        private store: Store<AppState>
    ) {

    }

    ngOnInit() {
        this.roles = Object.keys(Role);
        this.dialog = {
            title: this.translate.get('SHARED.DIALOG.USER_EDIT.TITLE'),
            form: this.builder.group({
                firstName: new FormControl({ value: this.user.firstName, disabled: true }, [
                    Validators.required
                ]),
                lastName: new FormControl({ value: this.user.lastName, disabled: true }, [
                    Validators.required
                ]),
                email: new FormControl({ value: this.user.email, disabled: true }, [
                    Validators.required,
                    Validators.email
                ]),
                role: new FormControl(this.user.role, [
                    Validators.required
                ]),
                active: new FormControl({ value: this.user.active, disabled: true }, [
                    Validators.required
                ]),
                enabled: new FormControl(this.user.enabled, [
                    Validators.required
                ])
            }),
            close: {
                type: DialogButtonType.OUTLINE_WARNING,
                label: this.translate.get('SHARED.DIALOG.USER_EDIT.CANCEL'),
                action: () => this.store.dispatch(new fromDialog.CloseDialogAction()),
                disabled: () => this.store.pipe(select(selectResourceIsUpdating<User>('users')))
            },
            submit: {
                type: DialogButtonType.OUTLINE_PRIMARY,
                label: this.translate.get('SHARED.DIALOG.USER_EDIT.SUBMIT'),
                action: () => this.store.dispatch(new fromSdr.PatchResourceAction('users', {
                    // TODO: come up with strategy to strip off disabled properies during patch, requires HATEOS self links
                    resource: Object.assign(this.user, this.dialog.form.value)
                })),
                disabled: () => combineLatest([
                    scheduled([this.dialog.form.invalid], queue),
                    scheduled([this.dialog.form.pristine], queue),
                    this.store.pipe(select(selectResourceIsUpdating<User>('users')))
                ]).pipe(map(results => results[0] || results[1] || results[2]))
            }
        };
    }

    public getRoleValue(role: string): string {
        return Role[role];
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
                    default: return scheduled(['unknown error'], queue);
                }
            }
        }
    }

}
