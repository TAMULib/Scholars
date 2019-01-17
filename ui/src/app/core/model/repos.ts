import { InjectionToken } from '@angular/core';
import { UserRepo } from './user';
import { ThemeRepo } from './theme';

// NOTE: the keys must match the property of the Spring Data REST embedded response

export const keys = {
    themes: 'name',
    users: 'email'
};

export const repos = {
    themes: new InjectionToken<string>('ThemeRepo'),
    users: new InjectionToken<string>('UserRepo')
};

export const injectable = [
    { provide: repos.themes, useExisting: ThemeRepo },
    { provide: repos.users, useExisting: UserRepo }
];

