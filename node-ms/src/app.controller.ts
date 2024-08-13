import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';

@Controller('student')
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  async getStudents() {
    return await this.appService.getStudents();
  }
}
