import { InjectionToken } from '@angular/core';
import { ConceptRepo } from './concept';
import { DocumentRepo } from './document';
import { OrganizationRepo } from './organization';
import { PersonRepo } from './person';
import { ProcessRepo } from './process';
import { RelationshipRepo } from './relationship';
import { ThemeRepo } from './theme';
import { UserRepo } from './user';

// NOTE: the keys must match the property of the Spring Data REST embedded response

export const keys = {
    concepts: 'name',
    documents: 'title',
    organizations: 'name',
    persons: 'name',
    processes: 'title',
    relationships: 'title',
    themes: 'name',
    users: 'email'
};

export const repos = {
    concepts: new InjectionToken<string>('ConceptRepo'),
    documents: new InjectionToken<string>('DocumentRepo'),
    organizations: new InjectionToken<string>('OrganizationRepo'),
    persons: new InjectionToken<string>('PersonRepo'),
    processes: new InjectionToken<string>('ProcessRepo'),
    relationships: new InjectionToken<string>('RelationshipRepo'),
    themes: new InjectionToken<string>('ThemeRepo'),
    users: new InjectionToken<string>('UserRepo')
};

export const injectable = [
    { provide: repos.concepts, useExisting: ConceptRepo },
    { provide: repos.documents, useExisting: DocumentRepo },
    { provide: repos.organizations, useExisting: OrganizationRepo },
    { provide: repos.persons, useExisting: PersonRepo },
    { provide: repos.processes, useExisting: ProcessRepo },
    { provide: repos.relationships, useExisting: RelationshipRepo },
    { provide: repos.themes, useExisting: ThemeRepo },
    { provide: repos.users, useExisting: UserRepo }
];
