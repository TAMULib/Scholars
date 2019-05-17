import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filter'
})
export class FilterPipe implements PipeTransform {

    transform(items: any[], filter: any): any[] {
        if (!items) {
            return [];
        }
        if (!filter) {
            return items;
        }
        return items.filter((item: any) => {
            for (const key in filter) {
                if (filter.hasOwnProperty(key)) {
                    if (item[key] !== filter[key]) {
                        return false;
                    }
                }
            }
            return true;
        });
    }

}
