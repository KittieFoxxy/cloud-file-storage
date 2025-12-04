<h1 align="center">
    Облачное хранилище файлов
</h1>

<p align="center">
    <a href="https://github.com/KittieFoxxy/cloud-file-storage/commits/" title="Last Commit"><img src="https://img.shields.io/github/last-commit/KittieFoxxy/cloud-file-storage?style=flat" alt="last commit"></a>
    <a href="https://github.com/KittieFoxxy/cloud-file-storage/blob/HEAD/LICENSE" title="License"><img src="https://img.shields.io/badge/License-MIT-green.svg?style=flat" alt="MIT License"></a>
</p>

Многопользовательское файловое облако.
Пользователи сервиса могут использовать его для загрузки и хранения файлов.
Источником вдохновения для проекта является Google Drive.

## Development
### Docker-Based Deployment
Если вы хотите использовать развертывание на основе Docker, вам нужно установить Docker на свою локальную машину. Для получения дополнительной информации о загрузке и установке Docker смотрите [официальную документацию Docker](https://docs.docker.com/get-docker/).

#### Полный запуск проекта (frontend + backend) используя Docker Compose
Этот способ предназначен для ситуаций, когда вам требуется запустить весь проект целиком, включая как backend (серверную часть), так и frontend (клиентскую часть).

Для удобства был подготовлен полный набор настроек для локальной разработки и тестирования с использованием [Docker Compose](https://docs.docker.com/compose/reference/overview/).
Чтобы собрать все модули проекта, выполните команду ```docker-compose build``` в корневой директории репозитория. 
Файл конфигурации по умолчанию можно найти в ```.env``` в корневой папке репозитория. 
Если конечные точки (endpoints) должны быть доступны в сети, перед запуском docker-compose необходимо изменить значения по умолчанию в этом файле (по умолчанию доступно только для website).

После того как сервисы будут собраны, вы можете запустить весь проект, используя команду ```docker-compose up```.

Docker-compose содержит следующие сервисы:

| Сервисы  | Описание                                                      | Endpoint                |
|----------|---------------------------------------------------------------|-------------------------|
| server   | Сервер - backend-часть Облачного хранилища файлов             | NO ENDPOINT             |
| website  | Вебсайт - frontend-часть Облачного хранилища файлов           | `http://localhost:8080` |
| postgres | Установка базы данных [Postgres](https://www.postgresql.org/) | NO ENDPOINT             |

#### Запуск только backend-части используя Docker Compose
Этот способ предназначен для сценариев, когда вам необходимо запустить только backend проекта с интеграцией внешнего frontend (например, [такого](https://github.com/zhukovsd/cloud-storage-frontend/tree/master))

## API Сервиса
API, предоставляемый backend-сервисом, документирован в спецификации [OpenAPI](https://www.openapis.org/). Файлы спецификации доступны по адресу (локации): [server/src/api.yaml](https://github.com/KittieFoxxy/cloud-file-storage/blob/main/server/src/api.yaml)
