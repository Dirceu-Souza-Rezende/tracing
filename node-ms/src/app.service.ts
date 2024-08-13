import { HttpService } from '@nestjs/axios';
import { Injectable } from '@nestjs/common';
import { AxiosError } from 'axios';
import { catchError, firstValueFrom } from 'rxjs';

@Injectable()
export class AppService {
  constructor(private readonly httpService: HttpService) {}
  async getStudents() {
    const { data } = await firstValueFrom(
      this.httpService.get('http://localhost:8080/student').pipe(
        catchError((error: AxiosError) => {
          console.log('error', error);
          throw 'An error happened!';
        }),
      ),
    );
    return data;
  }
}
