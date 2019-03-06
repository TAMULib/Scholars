import { InjectionToken } from '@angular/core';

import {
    ConceptRepo,
    DocumentRepo,
    OrganizationRepo,
    PersonRepo,
    ProcessRepo,
    RelationshipRepo
} from './discovery';

import { ThemeRepo } from './theme';
import { UserRepo } from './user';

import {
    DirectoryViewRepo,
    DiscoveryViewRepo
} from './view';

// NOTE: the keys must match the property of the Spring Data REST embedded response

export const keys = {
    concepts: 'name',
    documents: 'title',
    organizations: 'name',
    persons: 'name',
    processes: 'title',
    relationships: 'title',
    themes: 'name',
    users: 'email',
    directoryViews: 'name',
    discoveryViews: 'name'
};

export const repos = {
    concepts: new InjectionToken<string>('ConceptRepo'),
    documents: new InjectionToken<string>('DocumentRepo'),
    organizations: new InjectionToken<string>('OrganizationRepo'),
    persons: new InjectionToken<string>('PersonRepo'),
    processes: new InjectionToken<string>('ProcessRepo'),
    relationships: new InjectionToken<string>('RelationshipRepo'),
    themes: new InjectionToken<string>('ThemeRepo'),
    users: new InjectionToken<string>('UserRepo'),
    directoryViews: new InjectionToken<string>('DirectoryViewRepo'),
    discoveryViews: new InjectionToken<string>('DiscoveryViewRepo')
};

export const injectable = [
    { provide: repos.concepts, useExisting: ConceptRepo },
    { provide: repos.documents, useExisting: DocumentRepo },
    { provide: repos.organizations, useExisting: OrganizationRepo },
    { provide: repos.persons, useExisting: PersonRepo },
    { provide: repos.processes, useExisting: ProcessRepo },
    { provide: repos.relationships, useExisting: RelationshipRepo },
    { provide: repos.themes, useExisting: ThemeRepo },
    { provide: repos.users, useExisting: UserRepo },
    { provide: repos.directoryViews, useExisting: DirectoryViewRepo },
    { provide: repos.discoveryViews, useExisting: DiscoveryViewRepo }
];
