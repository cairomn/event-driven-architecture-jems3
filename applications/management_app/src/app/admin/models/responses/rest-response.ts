export interface RestResponse<T> {
  status: string;
  code: number;
  message: string;
  data: T;
}