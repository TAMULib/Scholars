import { InjectionToken } from '@angular/core';

import { ConceptRepo } from './discovery/repo/concept.repo';
import { DocumentRepo } from './discovery/repo/document.repo';
import { OrganizationRepo } from './discovery/repo/organization.repo';
import { PersonRepo } from './discovery/repo/person.repo';
import { ProcessRepo } from './discovery/repo/process.repo';
import { RelationshipRepo } from './discovery/repo/relationship.repo';
import { ThemeRepo } from './theme/repo/theme.repo';
import { UserRepo } from './user/repo/user.repo';
import { DirectoryViewRepo } from './view/repo/directory-view.repo';
import { DiscoveryViewRepo } from './view/repo/discovery-view.repo';
import { DisplayViewRepo } from './view/repo/display-view.repo';

// NOTE: the keys must match the property of the Spring Data REST embedded response

export const keys = {
    concepts: 'id',
    documents: 'id',
    organizations: 'id',
    persons: 'id',
    processes: 'id',
    relationships: 'id',
    themes: 'name',
    users: 'email',
    directoryViews: 'name',
    discoveryViews: 'name',
    displayViews: 'type'
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
    discoveryViews: new InjectionToken<string>('DiscoveryViewRepo'),
    displayViews: new InjectionToken<string>('DisplayViewRepo')
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
    { provide: repos.discoveryViews, useExisting: DiscoveryViewRepo },
    { provide: repos.displayViews, useExisting: DisplayViewRepo }
];
