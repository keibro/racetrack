dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "grooser"
    password = "grooser"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
//            url = "jdbc:hsqldb:mem:devDB"
            url = "jdbc:mysql://localhost:3306/racetrack_dev?autoreconnect=true"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost:3306/racetrack_test?autoreconnect=true"

        }
    }
    production {
        dataSource {
            dbCreate = "update"

            dialect = org.hibernate.dialect.MySQL5InnoDBDialect

            uri = new URI(System.env.CLEARDB_DATABASE_URL_A?:"mysql://keith.brophy@gmail.com:tndFRMGBZ07apU@localhost/test")

            url = "jdbc:mysql://"+uri.host+uri.path
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }
}
