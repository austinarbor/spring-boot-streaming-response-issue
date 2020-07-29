# spring-boot-streaming-response-issue

## Notes
 - I can't figure out why the controller methods are returning 200 when called from tests. 
 It seems as though the `@ExceptionHandler` methods are not invoked when running from tests, or my setup is botched.
 