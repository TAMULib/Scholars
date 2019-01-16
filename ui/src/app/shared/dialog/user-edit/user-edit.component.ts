import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Store, select } from '@ngrx/store';

import { combineLatest, of } from 'rxjs';
import { map } from 'rxjs/operators';

import { AppState } from '../../../core/store';
import { DialogButtonType, DialogControl } from '../../../core/store/dialog/dialog.model';
import { Role, User } from '../../../core/model/user';

import { selectReousrceIsUpdating } from '../../../core/store/sdr';

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
        private store: Store<AppState>
    ) {

    }

    ngOnInit() {
        this.roles = Object.keys(Role);
        this.dialog = {
            title: 'User Edit',
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
                label: 'Cancel',
                action: () => this.store.dispatch(new fromDialog.CloseDialogAction()),
                disabled: () => this.store.pipe(select(selectReousrceIsUpdating<User>('users')))
            },
            submit: {
                type: DialogButtonType.OUTLINE_PRIMARY,
                label: 'Update',
                action: () => this.store.dispatch(new fromSdr.PatchResourceAction('users', {
                    // TODO: come up with strategy to strip off disabled properies during patch, requires HATEOS self links
                    resource: Object.assign(this.user, this.dialog.form.value)
                })),
                disabled: () => combineLatest(
                    of(this.dialog.form.invalid),
                    of(this.dialog.form.pristine),
                    this.store.pipe(select(selectReousrceIsUpdating<User>('users')))
                ).pipe(map(results => results[0] || results[1] || results[2]))
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

    public getErrorMessage(field: string): string {
        const errors = this.dialog.form.controls[field].errors;
        for (const validation in errors) {
            if (errors.hasOwnProperty(validation)) {
                switch (validation) {
                    case 'required': return `${field} is required`;
                    case 'email': return `${field} must be a valid email`;
                    default: return 'unknown error';
                }
            }
        }
    }

}
