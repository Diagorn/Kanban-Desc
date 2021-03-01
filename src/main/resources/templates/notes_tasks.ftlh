<#import "parts/common.ftlh" as c>

<@c.page>
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
        <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
            <a class="navbar-brand brand-logo mr-5" href="/"><img src="images/logo.png" class="mr-2" alt="logo"/></a>
            <a class="navbar-brand brand-logo-mini" href="/"><img src="images/logo-mini.svg" alt="logo"/></a>
        </div>
        <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
            <ul class="navbar-nav navbar-nav-left">
                <li class="nav-item"><a href="/">Календарь</a></li>
                <li class="nav-item"><a href="/table/">Список заметок</a></li>
            </ul>
            <ul class="navbar-nav navbar-nav-right">
                <li class="nav-item nav-profile dropdown">
                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
                        <img src="images/faces/face.png" alt="profile"/>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
                        <a href="#" class="dropdown-item">
                            <i class="ti-power-off text-primary"></i>
                            Выход
                        </a>
                        <a href="#" class="dropdown-item">
                            <!-- ${token} -->
                        </a>
                    </div>
                </li>
                <li class="nav-item nav-settings d-none d-lg-flex">
                    <a class="nav-link" href="#">
                        <i class="icon-ellipsis"></i>
                    </a>
                </li>
            </ul>
            <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
                <span class="icon-menu"></span>
            </button>
        </div>
    </nav>

    <div class="container-fluid page-body-wrapper">

        <div class="main-panel">
            <div class="content-wrapper">

                <div class="row">
                    <div class="col-12 mb-4 mb-xl-0">
                        <h3 class="font-weight-bold">Ваши задачи и заметки:</h3>
                    </div>
                </div>

                <div class="row">
                    <div class="mb-4 mb-xl-0 col-6">
                        <h5 class="font-weight-bold">Задачи</h5>
                    </div>
                    <div class="mb-4 mb-xl-0 col-6">
                        <h5 class="font-weight-bold">Заметки</h5>
                    </div>
                </div>

                <div class="row">
                    <div class="mb-4 mb-xl-0 col-6">
                        <div id="tasks_handler" class="tasks-handler">
                            <#list tasks as task>
                                <div class="card mb-5" style="width: 18rem;">
                                    <div class="card-header">
                                        ${task.title}
                                    </div>
                                    <p class="card-text mb-5 pt-2 pl-2">${task.description}</p>
                                    <span class="left">${utils.getCalendarAsString(task.startDate)}</span>
                                    <span class="right">${utils.getCalendarAsString(task.endDate)}</span>
                                </div>
                            </#list>
                        </div>
                    </div>
                    <div class="mb-4 mb-xl-0 col-6">
                        <div class="notes-handler" id="notes_handler">
                            <#list notes as note>
                                <div class="card mb-5" style="width: 18rem;">
                                    <div class="card-header">
                                        ${note.title}
                                    </div>
                                    <p class="card-text mb-5 pt-2 pl-2">${note.description}</p>
                                    <span class="left">${utils.getCalendarAsString(note.startDate)}</span>
                                    <span class="right">${utils.getCalendarAsString(note.endDate)}</span>
                                    <!--<button class="btn btn-danger" onclick="deleteNote(${note.id})">Удалить</button>-->
                                </div>
                            </#list>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script src="/static/js/webix.min.js"></script>
    <script src="/static/js/dhtmlxscheduler.js"></script>
    <script src="/static/js/locale/locale_ru.js" charset="utf-8"></script>

    <script type="text/javascript" charset="utf-8">
        const tasks_handler = document.getElementById("tasks_handler");
        const notes_handler = document.getElementById("notes_handler");

        async function updateNote(note) {
            return await fetch("/table/notes/" + note.id,
                {
                    method: "PATCH",
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8',
                        '${_csrf.headerName}': '${_csrf.token}'
                    },
                    body: JSON.stringify(note)
                }).then(result => result.json());
        }
        async function createNote(note) {
            return await fetch("/table/notes/",
                {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8',
                        '${_csrf.headerName}': '${_csrf.token}'

                    },
                    body: JSON.stringify(note)
                }).then(result => result.json());
        }
        async function deleteNote(id) {
            return await fetch("/table/notes/" +id,
                {
                    method: "DELETE",
                    headers: {
                        '${_csrf.headerName}': '${_csrf.token}'
                    }
                });
        }
    </script>
</@c.page>