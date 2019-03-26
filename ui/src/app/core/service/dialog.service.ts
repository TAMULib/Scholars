import { Injectable } from '@angular/core';
import { NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';

import { FacetEntriesComponent } from '../../shared/dialog/facet-entries/facet-entries.component';
import { LoginComponent } from '../../shared/dialog/login/login.component';
import { NotificationComponent } from '../../shared/dialog/notification/notification.component';
import { UserEditComponent } from '../../shared/dialog/user-edit/user-edit.component';
import { RegistrationStep, RegistrationComponent } from '../../shared/dialog/registration/registration.component';

import { User } from '../model/user';
import { RegistrationRequest } from '../model/request';
import { SdrFacet } from '../model/sdr';

import * as fromDialog from '../../core/store/dialog/dialog.actions';

@Injectable({
    providedIn: 'root',
})
export class DialogService {

    constructor(private translate: TranslateService) {

    }

    // NOTE: using translate.instant requires the translation json be loaded before

    public loginDialog(): fromDialog.OpenDialogAction {
        return new fromDialog.OpenDialogAction({
            dialog: {
                ref: {
                    component: LoginComponent,
                    inputs: {}
                },
                options: this.options(this.translate.instant('SHARED.DIALOG.LOGIN.ARIA_LABELLED_BY'))
            }
        });
    }

    public registrationDialog(
        step: RegistrationStep,
        registration: RegistrationRequest
    ): fromDialog.OpenDialogAction {
        return new fromDialog.OpenDialogAction({
            dialog: {
                ref: {
                    component: RegistrationComponent,
                    inputs: { step, registration }
                },
                options: this.options(this.translate.instant('SHARED.DIALOG.REGISTRATION.ARIA_LABELLED_BY'))
            }
        });
    }

    public userEditDialog(user: User): fromDialog.OpenDialogAction {
        return new fromDialog.OpenDialogAction({
            dialog: {
                ref: {
                    component: UserEditComponent,
                    inputs: { user }
                },
                options: this.options(this.translate.instant('SHARED.DIALOG.USER_EDIT.ARIA_LABELLED_BY'))
            }
        });
    }

    public notificationDialog(text: string): fromDialog.OpenDialogAction {
        return new fromDialog.OpenDialogAction({
            dialog: {
                ref: {
                    component: NotificationComponent,
                    inputs: { text }
                },
                options: this.options(this.translate.instant('SHARED.DIALOG.NOTIFICATION.ARIA_LABELLED_BY'))
            }
        });
    }

    public facetEntriesDialog(name: string, facet: SdrFacet): fromDialog.OpenDialogAction {
        return new fromDialog.OpenDialogAction({
            dialog: {
                ref: {
                    component: FacetEntriesComponent,
                    inputs: { name, facet }
                },
                options: this.options(this.translate.instant('SHARED.DIALOG.FACET_ENTRIES.ARIA_LABELLED_BY'))
            }
        });
    }

    private options(
        ariaLabelledBy: string,
        centered = false,
        backdrop: boolean | 'static' = 'static'
    ): NgbModalOptions {
        return {
            ariaLabelledBy: ariaLabelledBy,
            centered: centered,
            backdrop: backdrop
        };
    }

}
