import { MatPaginatorIntl } from '@angular/material/paginator';
import { Injectable } from "@angular/core";

@Injectable()
export class MatPaginatorIntlPtBr extends MatPaginatorIntl {

    /** A label for the page size selector. */
    override itemsPerPageLabel: string = 'Itens por página:';

}