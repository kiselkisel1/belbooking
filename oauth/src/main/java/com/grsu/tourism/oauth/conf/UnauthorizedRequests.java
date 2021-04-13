package com.grsu.tourism.oauth.conf;

public class UnauthorizedRequests {
    public static final String[] urls = new String[]
            {
                    "/locations/get",
                    "/locations/region/get",
                    "/locations/getById",
                    "/locations/region/getById",

                    "/picture/getAll",
                    "/picture/getByServiceId/{serviceId}",
                    "/picture/get/{filename:.+}",

                    "/services/type",
                    "/services/subType",
                    "/services/getById",

                    "/stocks/get",

                    "/openingHours/get",
                    "/openingHours/getById",

                    "/comment/getById",
                    "/comment/getAll",
                    "/comment/getByServiceId/{serviceId}"

            };

}
