@import hexlet.code.model.Url
@import hexlet.code.model.UrlCheck
@param Url url

<html lang="ru">
<head>
    <div class="p-3 mb-2 bg-dark text-white">
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
              crossorigin="anonymous">
    </div>
    @if(url.getFlash() == null))
        <div class="alert alert-danger" role="alert">
            ${url.getFlash()}
        </div>
    @endif
    <h1>
        Сайт: ${url.getName()}
    </h1>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="row">Id:</th>
        <td>${url.getId()}</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">Имя: </th>
        <td>${url.getName()}</td>
    </tr>
    <tr>
        <th scope="row">Дата создания: </th>
        <td>${url.getCreatedAt().toString()}</td>
    </tr>
    </tbody>
</table>
    <h1>
        Проверки
    </h1>
    <br>
    <form action="/urls/${url.getId()}/checks" method="post">
        <div>
            <label>
                <p1>
                    <button type="submit" class="btn btn-primary">Запустить проверку</button>
                </p1>
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
    @if(url.getListOfChecks() != null)
        @for(UrlCheck check: url.getListOfChecks())
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
