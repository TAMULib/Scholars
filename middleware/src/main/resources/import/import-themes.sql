INSERT INTO THEMES (ID, ACTIVE, BANNER_ALT_TEXT, BANNER_IMAGE_URI, NAVBAR_BRAND_TEXT, NAVBAR_BRAND_URI, NAVBAR_LOGO_URI, HEROES_NAVIGABLE, ORGANIZATION, NAME) VALUES (1, true, 'SCHOLARS@TAMU', '/assets/images/scholars_logo_white.png', 'Texas A&M University Libraries', 'https://library.tamu.edu', '/assets/images/tamu-logo-with-right-border.png', false, 'Texas A&M University', 'TAMU');

INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--blue', '#007bff');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--indigo', '#6610f2');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--purple', '#6f42c1');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--pink', '#e83e8c');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--red', '#dc3545');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--orange', '#fd7e14');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--yellow', '#ffc107');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--green', '#28a745');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--teal', '#20c997');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--cyan', '#17a2b8');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--white', '#ffffff');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--gray', '#6c757d');
INSERT INTO THEME_COLORS (THEME_ID, KEY, VALUE) VALUES (1, '--gray-dark', '#343a40');

INSERT INTO THEME_VARIANTS (THEME_ID, KEY, VALUE) VALUES (1, '--primary', '#500000');
INSERT INTO THEME_VARIANTS (THEME_ID, KEY, VALUE) VALUES (1, '--secondary', '#65a6d1');
INSERT INTO THEME_VARIANTS (THEME_ID, KEY, VALUE) VALUES (1, '--success', '#28a745');
INSERT INTO THEME_VARIANTS (THEME_ID, KEY, VALUE) VALUES (1, '--info', '#17a2b8');
INSERT INTO THEME_VARIANTS (THEME_ID, KEY, VALUE) VALUES (1, '--warning', '#ffc107');
INSERT INTO THEME_VARIANTS (THEME_ID, KEY, VALUE) VALUES (1, '--danger', '#dc3545');
INSERT INTO THEME_VARIANTS (THEME_ID, KEY, VALUE) VALUES (1, '--light', '#f8f9fa');
INSERT INTO THEME_VARIANTS (THEME_ID, KEY, VALUE) VALUES (1, '--dark', '#3c0000');

INSERT INTO THEME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--accent', '#ffc222');
INSERT INTO THEME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--link-color', '#007bff');
INSERT INTO THEME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--link-color-hover', '#0056b3');

INSERT INTO THEME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--navigation-background-color', '#ededed');
INSERT INTO THEME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--navigation-color', '#3c0000');
INSERT INTO THEME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--navigation-hover-background-color', '#4d4d4d');
INSERT INTO THEME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--navigation-hover-color', '#fff');

INSERT INTO THEME_HOME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--hero-color', '#f3f3f3');
INSERT INTO THEME_HOME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--hero-link-color', '#65a6d1');
INSERT INTO THEME_HOME_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--hero-link-color-hover', '#ffc222');

INSERT INTO THEME_NAVBAR_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--navbar-background-color', '#3c0000');
INSERT INTO THEME_NAVBAR_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--navbar-hover-background-color', '#ffc222');
INSERT INTO THEME_NAVBAR_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--navbar-color', '#fff');
INSERT INTO THEME_NAVBAR_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--navbar-hover-color', '#3c0000');
INSERT INTO THEME_NAVBAR_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--navbar-link-seperator-color', '#6a0000');

INSERT INTO THEME_BANNER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--banner-border-top', '1px #8d8d8d solid');
INSERT INTO THEME_BANNER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--banner-background-color', '#500000');
INSERT INTO THEME_BANNER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--banner-color', '#fff');

INSERT INTO THEME_FOOTER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--footer-border-top', '1px #8d8d8d solid');
INSERT INTO THEME_FOOTER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--footer-background-color', '#3c0000');
INSERT INTO THEME_FOOTER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--footer-color', '#fff');
INSERT INTO THEME_FOOTER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--footer-min-height', '52px');
INSERT INTO THEME_FOOTER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--footer-font-size', '14px');
INSERT INTO THEME_FOOTER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--copyright-background-color', '#f8f9fa');
INSERT INTO THEME_FOOTER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--copyright-color', '#2d2d2d');
INSERT INTO THEME_FOOTER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--copyright-min-height', '48px');
INSERT INTO THEME_FOOTER_VARIABLES (THEME_ID, KEY, VALUE) VALUES (1, '--copyright-font-size', '12px');


INSERT INTO THEME_NAVBAR_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'Hours', 'https://library.tamu.edu/about/hours.html');
INSERT INTO THEME_NAVBAR_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'Libraries', 'https://library.tamu.edu/about/index.html');
INSERT INTO THEME_NAVBAR_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'MyLibrary', 'https://library.tamu.edu/mylibrary');
INSERT INTO THEME_NAVBAR_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'Help', 'https://askus.library.tamu.edu');

INSERT INTO THEME_FOOTER_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'howdy.tamu.edu', 'https://howdy.tamu.edu/uPortal/normal/render.uP');
INSERT INTO THEME_FOOTER_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'Off-Campus Access', 'https://library.tamu.edu/services/tech_troubleshooting.html');
INSERT INTO THEME_FOOTER_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'Texas A&M University', 'http://tamu.edu');
INSERT INTO THEME_FOOTER_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'Site Policies', 'http://library.tamu.edu/about/compliance.html');
INSERT INTO THEME_FOOTER_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'Accessibility', 'https://library.tamu.edu/services/accessibility.html');
INSERT INTO THEME_FOOTER_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'Texas CREWS', 'http://www.thecb.state.tx.us/apps/txcrews');
INSERT INTO THEME_FOOTER_LINKS (THEME_ID, LABEL, URI) VALUES (1, 'Comments', 'https://askus.library.tamu.edu/contact/index');

INSERT INTO THEME_HEROES (THEME_ID, IMAGE_ALT, IMAGE_URI, FONT_COLOR, LINK_COLOR, LINK_HOVER_COLOR, INTERVAL, WATERMARK_IMAGE_URI, WATERMARK_TEXT, BASE_TEXT) VALUES (1, 'Texas A&M Acedemic Building', '/assets/images/academic-aerial.jpg', '#f3f3f3', '#65a6d1', '#ffc222', 30, '/assets/images/vector-world-map.png', 'worldwide collaboration', 'Thank you for visiting Scolars@TAMU (Beta Release). We have worked to create the first version of your profile and continue to explore additional data sources to enhance your profile. We request your participation to update your profile and improve its accuracy. If you have any questions or concerns about your profile page, please contact us at <a href="mailto:scholars@library.tamu.edu">scholars@library.tamu.edu</a>');
