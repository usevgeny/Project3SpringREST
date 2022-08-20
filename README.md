1. Проет представляет собой реализацию домашнего задания по созданию SpringREST API приложения и REST API клиента.
2. Для удобства тестирования также создан docker проект, включающий в себя базу данных Postgres и проект SpringREST API приложения ("Project3restConsumer").
3. Перед началом тестирования убедиться, что база данных содержит необходимые таблицы. Скрипт инициализации таблиц data.sql.
4. Для удобства тестирования можно использовать файл с коллекцией запросов POSTMAN "Project3.postman_collection.json".
5. Для тестирования клиента и построения графиков соответвуеющий проект("project3restClient") нужно запустить в IDE 
6. При необходимости запуска нескольких инстансов SpringREST пприложения использовать docker-compose-scalable.yml (docker-compose -f docker-compose-scalable.yml up --build --scale springrest=4)

