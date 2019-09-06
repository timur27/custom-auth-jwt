import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";



const API_URL = environment.apiUrl;

@Injectable()
export class AuthService{
    constructor(private http: HttpClient){}

    //TODO Write posts to server for register
}
