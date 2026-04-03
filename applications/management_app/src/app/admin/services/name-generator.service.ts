import { Injectable } from '@angular/core';
import { uniqueNamesGenerator, adjectives, colors, animals, NumberDictionary, Config, languages } from 'unique-names-generator';

@Injectable({
  providedIn: 'root'
})
export class NameGeneratorService {

  private _configuration: Config = {} as Config;

  constructor() {
    this._configuration = {
      dictionaries: [languages, animals, NumberDictionary.generate({ min: 1, max: 10000 })],
      style: 'lowerCase',
      separator: '-'
    };
  }

  public getRandomName(): string {
    return uniqueNamesGenerator(this._configuration);
  }
}
