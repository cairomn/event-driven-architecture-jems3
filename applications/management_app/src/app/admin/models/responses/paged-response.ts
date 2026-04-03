export interface PagedResponse<T> {
  page: number;
  size: number;
  content: T[];
  totalPages: number;
  totalElements: number;
  last: boolean;
}