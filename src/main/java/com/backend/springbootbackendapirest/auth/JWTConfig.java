package com.backend.springbootbackendapirest.auth;

public class JWTConfig {

    public static final String LLAVA_SECRETA = "alguna.clave.secreta.12345678";

    public static final String RSA_PRIVADA="-----BEGIN RSA PRIVATE KEY-----\r\n" +
    "MIIEpAIBAAKCAQEAuXlCQmS46KqdsfwTb4ZM4QVBjjtTO7Bl8dY9LQYf0Vo3qtob\r\n" +
    "0Knbib3mdskEGk6d2cVFRU+IXeUHDlerEvnK/OyOgCXKBR75PJNeHZKWiVgcIXWL\r\n" +
    "1UsNcPUnzaieMYCK+irPhymeHYjUYI+FY1djSU8qdjoChw8dBOTQSjJMifffT1Vk\r\n" +
    "z7uewbfXxeMPsx2/t8kyR+E6Gvp/GR0Srnf0O5zGXHcKRkR/r2cU/2aYMkoavlr+\r\n" +
    "mG/QZE8b43nnyBfyIPBx5YPyROHzwEbZibUu81GVHmMAHJZriknX+WWreeSzs+gQ\r\n" +
    "XCuYSeo9R9xxveJhpe895k4/KneslZT7S2e+6wIDAQABAoIBAQCDlt2Ev3NVV590\r\n" +
    "ZLThXjZzO8hsAfkmkxtw/h//LqYqYUi3hfFQFQL2ce3t/uZTR83EcKqZ5ziH7XMZ\r\n" +
    "IOf2GZWD5s2fa7At+/C2opOWhacaIJIsxvoDxTDvIHAyAFzkIB/6J59/dO6CY87S\r\n" +
    "B2GKvrhpjapBoRyj9/wNuc1xpsgY5bYrQOeFRvMtO1xyHGuOEQZzSb3zhmWHfewW\r\n" +
    "5a7MxxDtVY4jAkZHx2ZRXItUAh7KJz5J/7wjdQhELugE9oWXWYzMjflmDkcXQCQl\r\n" +
    "9LfKn+v4P5Zt9QjCtOYATkR11GjJZER20lkQzvn0+/w7xso9x9hwErmuRXaD4cKi\r\n" +
    "aTaR/DbxAoGBAPCKWOfzD3QCeeUJB+8e4gvUqtyf9D91hQKH6P5k1sSwrczbQMu6\r\n" +
    "vwvqkoyYI6jkO98B9XuBhIjM0dv4jOHo0vefqVIMOmkHuLgx/J8E/Vzpt4fsIwng\r\n" +
    "hCmEkEBj629enGPodEzTuhRlUcCSXwed2rsnN2/v2YFGcCJrjj+Au3Z5AoGBAMVk\r\n" +
    "44riVG+n4NINl76UcHHa75hZ1gOujUpIyxYsbY9iYCirUq4JKIl2d8eLqbNL4ttU\r\n" +
    "SHO6PBEYR0POMajrahB3ftIV7d1m8VpPb/BQ2DlS99OyakwGwXUXp9kzXx8FWOrz\r\n" +
    "hiGstDUZw8tFg6LMSB8Mhzkrm4lMT98EfpwmL1eDAoGANysT3wcBOVNED8K1fiNw\r\n" +
    "JtR856MMCu3yNrVjO8128J+xDPYQc2l+XcZMHFDchOTr0mcJnW0EL7gCPdhQv5N9\r\n" +
    "p2uIExhY4TPzKtPUI8iqL4AvJs6C7sX+qeMMjqgcHGvvpH5xBhndaMYgLPGlOAL9\r\n" +
    "YsVI0rSJBzxXhZ1zf58P38kCgYEAvj2ftyiua/YGDSughcKWJIT2yYQ7aMz/ywoh\r\n" +
    "ALvM05XiSw/i3WxiLymIAz8tTGnkt7GhKjfg0DDv7f95iO1qCXjBN0R/PRSLo5hP\r\n" +
    "r/KakZTO2K5pF+Ci7nDgCcsi78i9i6MsMKQ8AblVJEFYvrvW/mWapZTLkCWGDNBO\r\n" +
    "HbwHgHUCgYBpf3629U912Ufbh5mWp7V2/pSR6jqi7DwlhNUIP6Nugn8xJpQsqOi6\r\n" +
    "rU+xA5CW1G9L2PGlsncxHamtbuyV0VPpZgK0Orka5kfnKhJPdCJg0bDTZoX3WKl5\r\n" +
    "1i9MhrE+CSGt7zX5aPwX+1GncJVQKqtCjlDYGk0wzjMILcS1aJswqA==\r\n"+
    "-----END RSA PRIVATE KEY-----\r\n";
   


    public static final String RSA_PUBLICA="-----BEGIN PUBLIC KEY-----\r\n"  +
    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuXlCQmS46KqdsfwTb4ZM\r\n"  +
    "4QVBjjtTO7Bl8dY9LQYf0Vo3qtob0Knbib3mdskEGk6d2cVFRU+IXeUHDlerEvnK\r\n"  +
    "/OyOgCXKBR75PJNeHZKWiVgcIXWL1UsNcPUnzaieMYCK+irPhymeHYjUYI+FY1dj\r\n"  +
    "SU8qdjoChw8dBOTQSjJMifffT1Vkz7uewbfXxeMPsx2/t8kyR+E6Gvp/GR0Srnf0\r\n"  +
    "O5zGXHcKRkR/r2cU/2aYMkoavlr+mG/QZE8b43nnyBfyIPBx5YPyROHzwEbZibUu\r\n"  +
    "81GVHmMAHJZriknX+WWreeSzs+gQXCuYSeo9R9xxveJhpe895k4/KneslZT7S2e+\r\n"  +
    "6wIDAQAB\r\n" +
    "-----END PUBLIC KEY-----";

}
