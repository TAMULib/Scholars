import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { Store, select } from '@ngrx/store';

import { combineLatest, of, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { AppState } from '../../../core/store';
import { DialogButtonType, DialogControl } from '../../../core/model/dialog';
import { RegistrationRequest } from '../../../core/model/request/registration.request';

import { selectIsSubmittingRegistration, selectIsCompletingRegistration } from '../../../core/store/auth';

import * as fromAuth from '../../../core/store/auth/auth.actions';
import * as fromDialog from '../../../core/store/dialog/dialog.actions';

export enum RegistrationStep {
    SUBMIT = 'submit',
    COMPLETE = 'complete'
}

export function confirmPasswordValidator(password: FormControl): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
        return password.value !== control.value ? { 'confirmPassword': { value: control.value } } : null;
    };
}

@Component({
    selector: 'scholars-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

    @Input() step: RegistrationStep;

    @Input() registration: RegistrationRequest;

    public dialog: DialogControl;

    constructor(
        private builder: FormBuilder,
        private translate: TranslateService,
        private store: Store<AppState>,
    ) {

    }

    ngOnInit() {

        const password = new FormControl('', this.isComplete() ? [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(64)
        ] : []);

        this.dialog = {
            title: this.translate.get('SHARED.DIALOG.REGISTRATION.TITLE'),
            form: this.builder.group({
                firstName: new FormControl(this.isComplete() ? this.registration.firstName : '', this.isSubmit() ? [
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(64)
                ] : []),
                lastName: new FormControl(this.isComplete() ? this.registration.lastName : '', this.isSubmit() ? [
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(64)
                ] : []),
                email: new FormControl(this.isComplete() ? this.registration.email : '', this.isSubmit() ? [
                    Validators.required,
                    Validators.email
                ] : []),
                password: password,
                confirm: new FormControl('', this.isComplete() ? [
                    Validators.required,
                    Validators.minLength(8),
                    Validators.maxLength(64),
                    confirmPasswordValidator(password)
                ] : [])
            }),
            close: {
                type: DialogButtonType.OUTLINE_WARNING,
                label: this.translate.get('SHARED.DIALOG.REGISTRATION.CANCEL'),
                action: () => this.store.dispatch(new fromDialog.CloseDialogAction()),
                disabled: () => this.isCancelDisabled()
            },
            submit: {
                type: DialogButtonType.OUTLINE_PRIMARY,
                label: this.translate.get('SHARED.DIALOG.REGISTRATION.SUBMIT'),
                action: () => this.submit(),
                disabled: () => this.isSubmitDisabled()
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
                    case 'minlength': return this.translate.get('SHARED.DIALOG.VALIDATION.MIN_LENGTH', { field, min: errors[validation].requiredLength });
                    case 'maxlength': return this.translate.get('SHARED.DIALOG.VALIDATION.MAX_LENGTH', { field, max: errors[validation].requiredLength });
                    case 'confirmPassword': return this.translate.get('SHARED.DIALOG.VALIDATION.CONFIRM_PASSWORD', { field });
                    default: return of('unknown error');
                }
            }
        }
    }

    public isComplete(): boolean {
        return this.step === RegistrationStep.COMPLETE;
    }

    public isSubmit(): boolean {
        return this.step === RegistrationStep.SUBMIT;
    }

    private submit(): void {
        if (this.isSubmit()) {
            this.store.dispatch(new fromAuth.SubmitRegistrationAction({ registration: this.dialog.form.value }));
        } else if (this.isComplete()) {
            this.store.dispatch(new fromAuth.CompleteRegistrationAction({ registration: this.dialog.form.value }));
        } else {
            throw new Error('Unknown registration step!');
        }
    }

    private isSubmitDisabled(): Observable<boolean> {
        if (this.isSubmit()) {
            return combineLatest([
                of(this.dialog.form.invalid),
                of(this.dialog.form.pristine),
                this.store.pipe(select(selectIsSubmittingRegistration))
            ]).pipe(map(results => results[0] || results[1] || results[2]));
        } else if (this.isComplete()) {
            return combineLatest([
                of(this.dialog.form.invalid),
                of(this.dialog.form.pristine),
                this.store.pipe(select(selectIsCompletingRegistration))
            ]).pipe(map(results => results[0] || results[1] || results[2]));
        } else {
            throw new Error('Unknown registration step!');
        }
    }

    private isCancelDisabled(): Observable<boolean> {
        if (this.isSubmit()) {
            return this.store.pipe(select(selectIsSubmittingRegistration));
        } else if (this.isComplete()) {
            return this.store.pipe(select(selectIsCompletingRegistration));
        } else {
            throw new Error('Unknown registration step!');
        }
    }

}
