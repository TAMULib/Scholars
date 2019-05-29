const nameToHex: Map<string, string> = new Map<string, string>([
    ['aliceblue', '#f0f8ff'],
    ['antiquewhite', '#faebd7'],
    ['aqua', '#00ffff'],
    ['aquamarine', '#7fffd4'],
    ['azure', '#f0ffff'],
    ['beige', '#f5f5dc'],
    ['bisque', '#ffe4c4'],
    ['black', '#000000'],
    ['blanchedalmond', '#ffebcd'],
    ['blue', '#0000ff'],
    ['blueviolet', '#8a2be2'],
    ['brown', '#a52a2a'],
    ['burlywood', '#deb887'],
    ['cadetblue', '#5f9ea0'],
    ['chartreuse', '#7fff00'],
    ['chocolate', '#d2691e'],
    ['coral', '#ff7f50'],
    ['cornflowerblue', '#6495ed'],
    ['cornsilk', '#fff8dc'],
    ['crimson', '#dc143c'],
    ['cyan', '#00ffff'],
    ['darkblue', '#00008b'],
    ['darkcyan', '#008b8b'],
    ['darkgoldenrod', '#b8860b'],
    ['darkgray', '#a9a9a9'],
    ['darkgreen', '#006400'],
    ['darkgrey', '#a9a9a9'],
    ['darkkhaki', '#bdb76b'],
    ['darkmagenta', '#8b008b'],
    ['darkolivegreen', '#556b2f'],
    ['darkorange', '#ff8c00'],
    ['darkorchid', '#9932cc'],
    ['darkred', '#8b0000'],
    ['darksalmon', '#e9967a'],
    ['darkseagreen', '#8fbc8f'],
    ['darkslateblue', '#483d8b'],
    ['darkslategray', '#2f4f4f'],
    ['darkslategrey', '#2f4f4f'],
    ['darkturquoise', '#00ced1'],
    ['darkviolet', '#9400d3'],
    ['deeppink', '#ff1493'],
    ['deepskyblue', '#00bfff'],
    ['dimgray', '#696969'],
    ['dimgrey', '#696969'],
    ['dodgerblue', '#1e90ff'],
    ['firebrick', '#b22222'],
    ['floralwhite', '#fffaf0'],
    ['forestgreen', '#228b22'],
    ['fuchsia', '#ff00ff'],
    ['gainsboro', '#dcdcdc'],
    ['ghostwhite', '#f8f8ff'],
    ['gold', '#ffd700'],
    ['goldenrod', '#daa520'],
    ['gray', '#808080'],
    ['green', '#008000'],
    ['greenyellow', '#adff2f'],
    ['grey', '#808080'],
    ['honeydew', '#f0fff0'],
    ['hotpink', '#ff69b4'],
    ['indianred', '#cd5c5c'],
    ['indigo', '#4b0082'],
    ['ivory', '#fffff0'],
    ['khaki', '#f0e68c'],
    ['lavender', '#e6e6fa'],
    ['lavenderblush', '#fff0f5'],
    ['lawngreen', '#7cfc00'],
    ['lemonchiffon', '#fffacd'],
    ['lightblue', '#add8e6'],
    ['lightcoral', '#f08080'],
    ['lightcyan', '#e0ffff'],
    ['lightgoldenrodyellow', '#fafad2'],
    ['lightgray', '#d3d3d3'],
    ['lightgreen', '#90ee90'],
    ['lightgrey', '#d3d3d3'],
    ['lightpink', '#ffb6c1'],
    ['lightsalmon', '#ffa07a'],
    ['lightseagreen', '#20b2aa'],
    ['lightskyblue', '#87cefa'],
    ['lightslategray', '#778899'],
    ['lightslategrey', '#778899'],
    ['lightsteelblue', '#b0c4de'],
    ['lightyellow', '#ffffe0'],
    ['lime', '#00ff00'],
    ['limegreen', '#32cd32'],
    ['linen', '#faf0e6'],
    ['magenta', '#ff00ff'],
    ['maroon', '#800000'],
    ['mediumaquamarine', '#66cdaa'],
    ['mediumblue', '#0000cd'],
    ['mediumorchid', '#ba55d3'],
    ['mediumpurple', '#9370db'],
    ['mediumseagreen', '#3cb371'],
    ['mediumslateblue', '#7b68ee'],
    ['mediumspringgreen', '#00fa9a'],
    ['mediumturquoise', '#48d1cc'],
    ['mediumvioletred', '#c71585'],
    ['midnightblue', '#191970'],
    ['mintcream', '#f5fffa'],
    ['mistyrose', '#ffe4e1'],
    ['moccasin', '#ffe4b5'],
    ['navajowhite', '#ffdead'],
    ['navy', '#000080'],
    ['oldlace', '#fdf5e6'],
    ['olive', '#808000'],
    ['olivedrab', '#6b8e23'],
    ['orange', '#ffa500'],
    ['orangered', '#ff4500'],
    ['orchid', '#da70d6'],
    ['palegoldenrod', '#eee8aa'],
    ['palegreen', '#98fb98'],
    ['paleturquoise', '#afeeee'],
    ['palevioletred', '#db7093'],
    ['papayawhip', '#ffefd5'],
    ['peachpuff', '#ffdab9'],
    ['peru', '#cd853f'],
    ['pink', '#ffc0cb'],
    ['plum', '#dda0dd'],
    ['powderblue', '#b0e0e6'],
    ['purple', '#800080'],
    ['rebeccapurple', '#663399'],
    ['red', '#ff0000'],
    ['rosybrown', '#bc8f8f'],
    ['royalblue', '#4169e1'],
    ['saddlebrown', '#8b4513'],
    ['salmon', '#fa8072'],
    ['sandybrown', '#f4a460'],
    ['seagreen', '#2e8b57'],
    ['seashell', '#fff5ee'],
    ['sienna', '#a0522d'],
    ['silver', '#c0c0c0'],
    ['skyblue', '#87ceeb'],
    ['slateblue', '#6a5acd'],
    ['slategray', '#708090'],
    ['slategrey', '#708090'],
    ['snow', '#fffafa'],
    ['springgreen', '#00ff7f'],
    ['steelblue', '#4682b4'],
    ['tan', '#d2b48c'],
    ['teal', '#008080'],
    ['thistle', '#d8bfd8'],
    ['tomato', '#ff6347'],
    ['turquoise', '#40e0d0'],
    ['violet', '#ee82ee'],
    ['wheat', '#f5deb3'],
    ['white', '#ffffff'],
    ['whitesmoke', '#f5f5f5'],
    ['yellow', '#ffff00'],
    ['yellowgreen', '#9acd32']
]);

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
            color = nameToHex.get(color);
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
