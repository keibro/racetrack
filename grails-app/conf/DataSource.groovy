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

            dialect = org.hibernate.dialect.MySQLDialect

            uri = new URI(System.env.DATABASE_URL?:"mysql://e27c741202e7fa:ad8c85e5@us-mm-auto-dca-01.cleardb.com/heroku_e9c90e66920c016")

            url = "jdbc:"+uri.host+uri.path
//            username = uri.userInfo.split(":")[0]
//            password = uri.userInfo.split(":")[1]
        }
    }
}
