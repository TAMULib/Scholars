const nameToHex = (name: string): string => {
    let hex;
    switch (name) {
        case 'aliceblue': hex = '#f0f8ff'; break;
        case 'antiquewhite': hex = '#faebd7'; break;
        case 'aqua': hex = '#00ffff'; break;
        case 'aquamarine': hex = '#7fffd4'; break;
        case 'azure': hex = '#f0ffff'; break;
        case 'beige': hex = '#f5f5dc'; break;
        case 'bisque': hex = '#ffe4c4'; break;
        case 'black': hex = '#000000'; break;
        case 'blanchedalmond': hex = '#ffebcd'; break;
        case 'blue': hex = '#0000ff'; break;
        case 'blueviolet': hex = '#8a2be2'; break;
        case 'brown': hex = '#a52a2a'; break;
        case 'burlywood': hex = '#deb887'; break;
        case 'cadetblue': hex = '#5f9ea0'; break;
        case 'chartreuse': hex = '#7fff00'; break;
        case 'chocolate': hex = '#d2691e'; break;
        case 'coral': hex = '#ff7f50'; break;
        case 'cornflowerblue': hex = '#6495ed'; break;
        case 'cornsilk': hex = '#fff8dc'; break;
        case 'crimson': hex = '#dc143c'; break;
        case 'cyan': hex = '#00ffff'; break;
        case 'darkblue': hex = '#00008b'; break;
        case 'darkcyan': hex = '#008b8b'; break;
        case 'darkgoldenrod': hex = '#b8860b'; break;
        case 'darkgray': hex = '#a9a9a9'; break;
        case 'darkgreen': hex = '#006400'; break;
        case 'darkgrey': hex = '#a9a9a9'; break;
        case 'darkkhaki': hex = '#bdb76b'; break;
        case 'darkmagenta': hex = '#8b008b'; break;
        case 'darkolivegreen': hex = '#556b2f'; break;
        case 'darkorange': hex = '#ff8c00'; break;
        case 'darkorchid': hex = '#9932cc'; break;
        case 'darkred': hex = '#8b0000'; break;
        case 'darksalmon': hex = '#e9967a'; break;
        case 'darkseagreen': hex = '#8fbc8f'; break;
        case 'darkslateblue': hex = '#483d8b'; break;
        case 'darkslategray': hex = '#2f4f4f'; break;
        case 'darkslategrey': hex = '#2f4f4f'; break;
        case 'darkturquoise': hex = '#00ced1'; break;
        case 'darkviolet': hex = '#9400d3'; break;
        case 'deeppink': hex = '#ff1493'; break;
        case 'deepskyblue': hex = '#00bfff'; break;
        case 'dimgray': hex = '#696969'; break;
        case 'dimgrey': hex = '#696969'; break;
        case 'dodgerblue': hex = '#1e90ff'; break;
        case 'firebrick': hex = '#b22222'; break;
        case 'floralwhite': hex = '#fffaf0'; break;
        case 'forestgreen': hex = '#228b22'; break;
        case 'fuchsia': hex = '#ff00ff'; break;
        case 'gainsboro': hex = '#dcdcdc'; break;
        case 'ghostwhite': hex = '#f8f8ff'; break;
        case 'gold': hex = '#ffd700'; break;
        case 'goldenrod': hex = '#daa520'; break;
        case 'gray': hex = '#808080'; break;
        case 'green': hex = '#008000'; break;
        case 'greenyellow': hex = '#adff2f'; break;
        case 'grey': hex = '#808080'; break;
        case 'honeydew': hex = '#f0fff0'; break;
        case 'hotpink': hex = '#ff69b4'; break;
        case 'indianred': hex = '#cd5c5c'; break;
        case 'indigo': hex = '#4b0082'; break;
        case 'ivory': hex = '#fffff0'; break;
        case 'khaki': hex = '#f0e68c'; break;
        case 'lavender': hex = '#e6e6fa'; break;
        case 'lavenderblush': hex = '#fff0f5'; break;
        case 'lawngreen': hex = '#7cfc00'; break;
        case 'lemonchiffon': hex = '#fffacd'; break;
        case 'lightblue': hex = '#add8e6'; break;
        case 'lightcoral': hex = '#f08080'; break;
        case 'lightcyan': hex = '#e0ffff'; break;
        case 'lightgoldenrodyellow': hex = '#fafad2'; break;
        case 'lightgray': hex = '#d3d3d3'; break;
        case 'lightgreen': hex = '#90ee90'; break;
        case 'lightgrey': hex = '#d3d3d3'; break;
        case 'lightpink': hex = '#ffb6c1'; break;
        case 'lightsalmon': hex = '#ffa07a'; break;
        case 'lightseagreen': hex = '#20b2aa'; break;
        case 'lightskyblue': hex = '#87cefa'; break;
        case 'lightslategray': hex = '#778899'; break;
        case 'lightslategrey': hex = '#778899'; break;
        case 'lightsteelblue': hex = '#b0c4de'; break;
        case 'lightyellow': hex = '#ffffe0'; break;
        case 'lime': hex = '#00ff00'; break;
        case 'limegreen': hex = '#32cd32'; break;
        case 'linen': hex = '#faf0e6'; break;
        case 'magenta': hex = '#ff00ff'; break;
        case 'maroon': hex = '#800000'; break;
        case 'mediumaquamarine': hex = '#66cdaa'; break;
        case 'mediumblue': hex = '#0000cd'; break;
        case 'mediumorchid': hex = '#ba55d3'; break;
        case 'mediumpurple': hex = '#9370db'; break;
        case 'mediumseagreen': hex = '#3cb371'; break;
        case 'mediumslateblue': hex = '#7b68ee'; break;
        case 'mediumspringgreen': hex = '#00fa9a'; break;
        case 'mediumturquoise': hex = '#48d1cc'; break;
        case 'mediumvioletred': hex = '#c71585'; break;
        case 'midnightblue': hex = '#191970'; break;
        case 'mintcream': hex = '#f5fffa'; break;
        case 'mistyrose': hex = '#ffe4e1'; break;
        case 'moccasin': hex = '#ffe4b5'; break;
        case 'navajowhite': hex = '#ffdead'; break;
        case 'navy': hex = '#000080'; break;
        case 'oldlace': hex = '#fdf5e6'; break;
        case 'olive': hex = '#808000'; break;
        case 'olivedrab': hex = '#6b8e23'; break;
        case 'orange': hex = '#ffa500'; break;
        case 'orangered': hex = '#ff4500'; break;
        case 'orchid': hex = '#da70d6'; break;
        case 'palegoldenrod': hex = '#eee8aa'; break;
        case 'palegreen': hex = '#98fb98'; break;
        case 'paleturquoise': hex = '#afeeee'; break;
        case 'palevioletred': hex = '#db7093'; break;
        case 'papayawhip': hex = '#ffefd5'; break;
        case 'peachpuff': hex = '#ffdab9'; break;
        case 'peru': hex = '#cd853f'; break;
        case 'pink': hex = '#ffc0cb'; break;
        case 'plum': hex = '#dda0dd'; break;
        case 'powderblue': hex = '#b0e0e6'; break;
        case 'purple': hex = '#800080'; break;
        case 'rebeccapurple': hex = '#663399'; break;
        case 'red': hex = '#ff0000'; break;
        case 'rosybrown': hex = '#bc8f8f'; break;
        case 'royalblue': hex = '#4169e1'; break;
        case 'saddlebrown': hex = '#8b4513'; break;
        case 'salmon': hex = '#fa8072'; break;
        case 'sandybrown': hex = '#f4a460'; break;
        case 'seagreen': hex = '#2e8b57'; break;
        case 'seashell': hex = '#fff5ee'; break;
        case 'sienna': hex = '#a0522d'; break;
        case 'silver': hex = '#c0c0c0'; break;
        case 'skyblue': hex = '#87ceeb'; break;
        case 'slateblue': hex = '#6a5acd'; break;
        case 'slategray': hex = '#708090'; break;
        case 'slategrey': hex = '#708090'; break;
        case 'snow': hex = '#fffafa'; break;
        case 'springgreen': hex = '#00ff7f'; break;
        case 'steelblue': hex = '#4682b4'; break;
        case 'tan': hex = '#d2b48c'; break;
        case 'teal': hex = '#008080'; break;
        case 'thistle': hex = '#d8bfd8'; break;
        case 'tomato': hex = '#ff6347'; break;
        case 'turquoise': hex = '#40e0d0'; break;
        case 'violet': hex = '#ee82ee'; break;
        case 'wheat': hex = '#f5deb3'; break;
        case 'white': hex = '#ffffff'; break;
        case 'whitesmoke': hex = '#f5f5f5'; break;
        case 'yellow': hex = '#ffff00'; break;
        case 'yellowgreen': hex = '#9acd32'; break;
        default: hex = '#000000'; break;
    }
    return hex;
};

const expandHexShorthand = (hex: string): string => {
    // expand shorthand form (e.g. "03f") to full form (e.g. "0033ff")
    const shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
    return hex.replace(shorthandRegex, (m, r, g, b) => {
        return r + r + g + g + b + b;
    });
};

const colorToHex = (rgb: string): string => {
    // tslint:disable-next-line: max-line-length
    const matches = rgb.match(/^(rgb\((0|255|25[0-4]|2[0-4]\d|1\d\d|0?\d?\d),(0|255|25[0-4]|2[0-4]\d|1\d\d|0?\d?\d),(0|255|25[0-4]|2[0-4]\d|1\d\d|0?\d?\d)\)|rgba\((0|255|25[0-4]|2[0-4]\d|1\d\d|0?\d?\d),(0|255|25[0-4]|2[0-4]\d|1\d\d|0?\d?\d),(0|255|25[0-4]|2[0-4]\d|1\d\d|0?\d?\d),(0?\.\d|1(\.0)?)\)|hsl\((0|360|35\d|3[0-4]\d|[12]\d\d|0?\d?\d),(0|100|\d{1,2})%,(0|100|\d{1,2})%\)|hsla\((0|360|35\d|3[0-4]\d|[12]\d\d|0?\d?\d),(0|100|\d{1,2})%,(0|100|\d{1,2})%,(0?\.\d|1(\.0)?)\))$/);
    function hex(x) { return ('0' + parseInt(x, 10).toString(16)).slice(-2); }
    return (hex(matches[1]) + hex(matches[2]) + hex(matches[3])).toUpperCase();
};

const normalizeColor = (color: string): string => {
    color = color.trim();
    if (color.charAt(0) !== '#') {
        if (color.indexOf('rgb') !== 0) {
            color = nameToHex(color);
        } else {
            color = colorToHex(color);
        }
    } else {
        if (color.length === 4) {
            color = expandHexShorthand(color);
        }
    }
    return color.replace(/#/g, '');
};

const hexToRgb = (hex: string): any => {
    hex = normalizeColor(hex);
    const result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16)
    } : null;
};

const mix = (baseColor: string, color: string, weight: number): string => {
    baseColor = normalizeColor(baseColor);
    color = normalizeColor(color);
    function d2h(d) { return d.toString(16); } // convert a decimal value to hex
    function h2d(h) { return parseInt(h, 16); } // convert a hex value to decimal
    weight = (typeof (weight) !== 'undefined') ? weight : 50; // set the weight to 50%, if that argument is omitted
    let resultColor = '#';
    for (let i = 0; i <= 5; i += 2) { // loop through each of the 3 hex pairs—red, green, and blue
        const v1 = h2d(baseColor.substr(i, 2)); // extract the current pairs
        const v2 = h2d(color.substr(i, 2));
        // combine the current pairs from each source color, according to the specified weight
        let val = d2h(Math.round(v2 + (v1 - v2) * (weight / 100.0)));
        while (val.length < 2) { val = '0' + val; } // prepend a '0' if val results in a single digit
        resultColor += val; // concatenate val to our new color string
    }
    return resultColor;
};

// TODO: replace with closer match to sass darken and lighten
const luminance = (color: string, lum: number): string => {
    color = normalizeColor(color);
    lum = lum || 0;
    // convert to decimal and change luminosity
    let rgb = '#';
    let c;
    let i;
    for (i = 0; i < 3; i++) {
        c = parseInt(color.substr(i * 2, 2), 16);
        c = Math.round(Math.min(Math.max(0, c + (c * lum)), 255)).toString(16);
        rgb += ('00' + c).substr(c.length);
    }
    return rgb;
};

const yiq = (color: string): number => {
    const rgb = hexToRgb(color);
    return Math.round(((rgb.r * 299) + (rgb.g * 587) + (rgb.b * 114)) / 1000);
};

export {
    hexToRgb,
    luminance,
    mix,
    yiq
};
