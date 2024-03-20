@import hexlet.code.model.UrlCheck
@import hexlet.code.dto.UrlChecks
@param UrlChecks urlChecks

<html lang="ru">
<head>
    <div class="p-3 mb-2 bg-dark text-white">
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
              crossorigin="anonymous">
        <ul class="nav nav-underline">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/">Анализатор страниц</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/urls">Сайты</a>
            </li>
        </ul>
    </div>
    <h1>
        Сайт: ${urlChecks.url.getName()}
    </h1>
    @if(urlChecks.flash.isPresent())
        @if(urlChecks.flash.get().equals("Проверка прошла успешно!"))
            <div class="alert alert-success" role="alert">
                ${urlChecks.flash.get()}
            </div>
        @else
            @if(urlChecks.flash.get().equals("Некорректный url!"))
                <div class="alert alert-danger" role="alert">
                    ${urlChecks.flash.get()}
                </div>
            @endif
        @endif
    @endif
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="row">Id:</th>
        <td>${urlChecks.url.getId()}</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">Имя: </th>
        <td>${urlChecks.url.getName()}</td>
    </tr>
    <tr>
        <th scope="row">Дата создания: </th>
        <td>${urlChecks.url.getCreatedAt().toString()}</td>
    </tr>
    </tbody>
</table>
    <h1>
        Проверки
    </h1>
    <br>
    <form action="/urls/${urlChecks.url.getId()}/checks" method="post">
        <div>
            <label>
                <button type="submit" class="btn btn-primary">Запустить проверку</button>
            </label>
        </div>
    </form>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Код ответа</th>
        <th scope="col">title</th>
        <th scope="col">h1</th>
        <th scope="col">description</th>
        <th scope="col">Дата проверки</th>
    </tr>
    </thead>
    <tbody>
    @if(urlChecks.urlCheckList != null)
        @for(UrlCheck check: urlChecks.urlCheckList)
        <tr>
            <th scope="row"> ${check.getId()} </th>
            <td> ${check.getStatusCode()} </td>
            <td> ${check.getTitle()} </td>
            <td> ${check.getH1()} </td>
            <td> ${check.getDescription()} </td>
            <td> ${check.getCreatedAt().toString()} </td>
        </tr>
        @endfor
        @endif
        </tbody>
    </table>
    </body>
</html>
