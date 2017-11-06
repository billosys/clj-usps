# clj-usps

Clojure interface to the USPS Web Tools API


This is a fork of
[https://github.com/banzai-inc/usps-clj](https://github.com/banzai-inc/usps-clj).


## Install

``` clojure
[systems.billo/clj-usps "1.0.0-SNAPSHOT"]
```


## Versions

Original repo:
* usps-clj 0.1.0
* usps-clj 0.1.1

This fork:
* systems.billo/clj-usps 0.2.0 - this is identical to usps-clj 0.1.1,
  modulo a name change
* systems.billo/clj-usps 0.3.0 - still API-identical, but with updated deps
* systems.billo/clj-usps 1.0.0+ - the beginning of breaking API changes


## Usage

### Create a Client

```clj
(require '[usps.api.core :as client])
(def usps-client (client/create))
```

This will read your USPS client user ID from either the environment or, failing
that, from a configuration file: `~/.usps/client.ini`. The config file is
expected to be in the following format:

```ini
[default]
user-id = abcdefg123456789
```


### Validate an Address

```clj
(require '[usps.api.address :as address])
(address/validate
  {:street "963 E 970 N" :state "UT" :city "Orem" :zip "84097"})
```

Returns the validated address.

The USPS API user ID is taken from either the environment variable
`USPS_USER_ID` or from the config/INI file indicated above.

If an error is encountered, it is printed to `stdout` but a full error data
structure is also returned.


## License

Copyright © 2017, Billo Systems, Ltd. Co.

Copyright © 2013 Banzai Inc.

Distributed under the Eclipse Public License, the same as Clojure.
