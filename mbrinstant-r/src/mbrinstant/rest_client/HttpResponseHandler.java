/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.rest_client;

import mbrinstant.exceptions.ServerException;

/**
 *
 * @author maine This class handles all http response from the redserver.
 */
public class HttpResponseHandler {

    private int code;
    private String message;
    private String description;

    public HttpResponseHandler() {
    }

    public HttpResponseHandler(int code) {
        this.code = code;
    }

    public boolean isSuccessful() throws ServerException {
        boolean result = false;
        //http status codes for successful request
        if (code >= 200 && code <= 206) {
            switch (code) {
                case 200:
                    setMessage("OK");
                    setDescription("Server request successful.");
                    break;
                case 201:
                    setMessage("CREATED");
                    setDescription("A resource has been successfully created.");
                    break;
                case 202:
                    setMessage("ACCEPTED");
                    setDescription("Request is accepted for processing.");
                    break;
                case 203:
                    setMessage("NON-AUTHORATIVE INFORMATION");
                    setDescription("Requested information came from a different server.");
                    break;
                case 204:
                    setMessage("NO CONTENT");
                    setDescription("Server request successful. No data is needed to be received.");
                    break;
                case 205:
                    setMessage("RESET CONTENT");
                    setDescription("A reset of previous content is requested from the server.");
                    break;
                default:
                    setMessage("Server request successful.");
                    setDescription("No additional details currently available for status code: " + code);
                    break;

            }
            System.out.println(this.toString());
            return true;
            //create notification for successful request here...

        } else if (code >= 300 && code <= 307) {//status codes for url redirection
            switch (code) {

                default:
                    setMessage("Redirecting...");
                    setDescription("");
                    break;
            }
        } else if (code >= 400 && code <= 417) {//status codes for client error
            switch (code) {
                case 400:
                    setMessage("BAD REQUEST");
                    setDescription("Server was not able to understand the request due to syntax error.");
                    throw new ServerException(code);

                case 401:
                    setMessage("UNAUTHORIZED");
                    setDescription("Client is not authorized by the server.");
                    break;
                case 403:
                    setMessage("FORBIDDEN");
                    setDescription("Access to the requested resource has been denied. Client is unauthorized.");

                    break;
                case 404:
                    setMessage("NOT FOUND");
                    setDescription("The requested resource was not found.");
                    throw new ServerException(code);
                case 405:
                    setMessage("METHOD NOT ALLOWED");
                    setDescription("The requested method is not allowed.");
                    throw new ServerException(code);
                default:
                    setMessage("SYSTEM/CLIENT ERROR");
                    setDescription("A system error has occured. Report is advised.");
                    throw new ServerException(code);
            }

        } else if (code >= 500 && code <= 505) {//status codes for server error
            switch (code) {
                default:
                    setMessage("SERVER ERROR");
                    setDescription("A server error has been encountered. Report is advised.");
                    throw new ServerException(code);
            }
            //create exception handler here...
        }
        System.out.println("HTTP Status \n" + this.toString() + "\n");
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String result = "CODE: " + code + ""
                + "\nMESSAGE: " + message + ""
                + "\nDESCRIPTION: " + description;

        return result;
    }

}
