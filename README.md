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


### Validate an Address

``` clojure
(require '[usps.api.address :as address])
(address/validate
  {:street "963 E 970 N" :state "UT" :city "Orem" :zip "84097"}
  usps-user-id)
```

Returns the validated address.

If an error is encountered, it is printed to `stdout` but a full error data
structure is also returned.


## License

Copyright © 2017, Billo Systems, Ltd. Co.
Copyright © 2013 Banzai Inc.

Distributed under the Eclipse Public License, the same as Clojure.
