const nameToHex = (name: string): string => {
    switch (name) {
        case 'aliceblue': return '#f0f8ff';
        case 'antiquewhite': return '#faebd7';
        case 'aqua': return '#00ffff';
        case 'aquamarine': return '#7fffd4';
        case 'azure': return '#f0ffff';
        case 'beige': return '#f5f5dc';
        case 'bisque': return '#ffe4c4';
        case 'black': return '#000000';
        case 'blanchedalmond': return '#ffebcd';
        case 'blue': return '#0000ff';
        case 'blueviolet': return '#8a2be2';
        case 'brown': return '#a52a2a';
        case 'burlywood': return '#deb887';
        case 'cadetblue': return '#5f9ea0';
        case 'chartreuse': return '#7fff00';
        case 'chocolate': return '#d2691e';
        case 'coral': return '#ff7f50';
        case 'cornflowerblue': return '#6495ed';
        case 'cornsilk': return '#fff8dc';
        case 'crimson': return '#dc143c';
        case 'cyan': return '#00ffff';
        case 'darkblue': return '#00008b';
        case 'darkcyan': return '#008b8b';
        case 'darkgoldenrod': return '#b8860b';
        case 'darkgray': return '#a9a9a9';
        case 'darkgreen': return '#006400';
        case 'darkgrey': return '#a9a9a9';
        case 'darkkhaki': return '#bdb76b';
        case 'darkmagenta': return '#8b008b';
        case 'darkolivegreen': return '#556b2f';
        case 'darkorange': return '#ff8c00';
        case 'darkorchid': return '#9932cc';
        case 'darkred': return '#8b0000';
        case 'darksalmon': return '#e9967a';
        case 'darkseagreen': return '#8fbc8f';
        case 'darkslateblue': return '#483d8b';
        case 'darkslategray': return '#2f4f4f';
        case 'darkslategrey': return '#2f4f4f';
        case 'darkturquoise': return '#00ced1';
        case 'darkviolet': return '#9400d3';
        case 'deeppink': return '#ff1493';
        case 'deepskyblue': return '#00bfff';
        case 'dimgray': return '#696969';
        case 'dimgrey': return '#696969';
        case 'dodgerblue': return '#1e90ff';
        case 'firebrick': return '#b22222';
        case 'floralwhite': return '#fffaf0';
        case 'forestgreen': return '#228b22';
        case 'fuchsia': return '#ff00ff';
        case 'gainsboro': return '#dcdcdc';
        case 'ghostwhite': return '#f8f8ff';
        case 'gold': return '#ffd700';
        case 'goldenrod': return '#daa520';
        case 'gray': return '#808080';
        case 'green': return '#008000';
        case 'greenyellow': return '#adff2f';
        case 'grey': return '#808080';
        case 'honeydew': return '#f0fff0';
        case 'hotpink': return '#ff69b4';
        case 'indianred': return '#cd5c5c';
        case 'indigo': return '#4b0082';
        case 'ivory': return '#fffff0';
        case 'khaki': return '#f0e68c';
        case 'lavender': return '#e6e6fa';
        case 'lavenderblush': return '#fff0f5';
        case 'lawngreen': return '#7cfc00';
        case 'lemonchiffon': return '#fffacd';
        case 'lightblue': return '#add8e6';
        case 'lightcoral': return '#f08080';
        case 'lightcyan': return '#e0ffff';
        case 'lightgoldenrodyellow': return '#fafad2';
        case 'lightgray': return '#d3d3d3';
        case 'lightgreen': return '#90ee90';
        case 'lightgrey': return '#d3d3d3';
        case 'lightpink': return '#ffb6c1';
        case 'lightsalmon': return '#ffa07a';
        case 'lightseagreen': return '#20b2aa';
        case 'lightskyblue': return '#87cefa';
        case 'lightslategray': return '#778899';
        case 'lightslategrey': return '#778899';
        case 'lightsteelblue': return '#b0c4de';
        case 'lightyellow': return '#ffffe0';
        case 'lime': return '#00ff00';
        case 'limegreen': return '#32cd32';
        case 'linen': return '#faf0e6';
        case 'magenta': return '#ff00ff';
        case 'maroon': return '#800000';
        case 'mediumaquamarine': return '#66cdaa';
        case 'mediumblue': return '#0000cd';
        case 'mediumorchid': return '#ba55d3';
        case 'mediumpurple': return '#9370db';
        case 'mediumseagreen': return '#3cb371';
        case 'mediumslateblue': return '#7b68ee';
        case 'mediumspringgreen': return '#00fa9a';
        case 'mediumturquoise': return '#48d1cc';
        case 'mediumvioletred': return '#c71585';
        case 'midnightblue': return '#191970';
        case 'mintcream': return '#f5fffa';
        case 'mistyrose': return '#ffe4e1';
        case 'moccasin': return '#ffe4b5';
        case 'navajowhite': return '#ffdead';
        case 'navy': return '#000080';
        case 'oldlace': return '#fdf5e6';
        case 'olive': return '#808000';
        case 'olivedrab': return '#6b8e23';
        case 'orange': return '#ffa500';
        case 'orangered': return '#ff4500';
        case 'orchid': return '#da70d6';
        case 'palegoldenrod': return '#eee8aa';
        case 'palegreen': return '#98fb98';
        case 'paleturquoise': return '#afeeee';
        case 'palevioletred': return '#db7093';
        case 'papayawhip': return '#ffefd5';
        case 'peachpuff': return '#ffdab9';
        case 'peru': return '#cd853f';
        case 'pink': return '#ffc0cb';
        case 'plum': return '#dda0dd';
        case 'powderblue': return '#b0e0e6';
        case 'purple': return '#800080';
        case 'rebeccapurple': return '#663399';
        case 'red': return '#ff0000';
        case 'rosybrown': return '#bc8f8f';
        case 'royalblue': return '#4169e1';
        case 'saddlebrown': return '#8b4513';
        case 'salmon': return '#fa8072';
        case 'sandybrown': return '#f4a460';
        case 'seagreen': return '#2e8b57';
        case 'seashell': return '#fff5ee';
        case 'sienna': return '#a0522d';
        case 'silver': return '#c0c0c0';
        case 'skyblue': return '#87ceeb';
        case 'slateblue': return '#6a5acd';
        case 'slategray': return '#708090';
        case 'slategrey': return '#708090';
        case 'snow': return '#fffafa';
        case 'springgreen': return '#00ff7f';
        case 'steelblue': return '#4682b4';
        case 'tan': return '#d2b48c';
        case 'teal': return '#008080';
        case 'thistle': return '#d8bfd8';
        case 'tomato': return '#ff6347';
        case 'turquoise': return '#40e0d0';
        case 'violet': return '#ee82ee';
        case 'wheat': return '#f5deb3';
        case 'white': return '#ffffff';
        case 'whitesmoke': return '#f5f5f5';
        case 'yellow': return '#ffff00';
        case 'yellowgreen': return '#9acd32';
        default: return '#000000';
    }
};

const expandHexShorthand = (hex: string): string => {
    // expand shorthand form (e.g. "03f") to full form (e.g. "0033ff")
    const shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
    return hex.replace(shorthandRegex, (m, r, g, b) => {
        return r + r + g + g + b + b;
    });
};

const rgbToHex = (rgb: string): string => {
    // regex that parses a string such as 'rgb(191, 0, 46)' and produces an array
    const matches = rgb.match(/^rgba?\((\d+),\s*(\d+),\s*(\d+)(?:,\s*([\d\.]+))?\)$/);
    function hex(x) { return ('0' + parseInt(x, 10).toString(16)).slice(-2); }
    return (hex(matches[1]) + hex(matches[2]) + hex(matches[3])).toUpperCase();
};

const normalizeColor = (color: string): string => {
    color = color.trim();
    if (color.charAt(0) !== '#') {
        if (color.indexOf('rgb') !== 0) {
            color = nameToHex(color);
        } else {
            color = rgbToHex(color);
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
