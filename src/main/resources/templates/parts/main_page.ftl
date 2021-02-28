<#macro main_page>
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
        <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
            <a class="navbar-brand brand-logo mr-5" href="/"><img src="images/logo.png" class="mr-2" alt="logo"/></a>
            <a class="navbar-brand brand-logo-mini" href="/"><img src="images/logo-mini.svg" alt="logo"/></a>
        </div>
        <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
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
                        <h3 class="font-weight-bold">Здравствуйте, ${user.firstName}</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div id="widgets-container" class="widgets-container">
                            <div id="widgetScheduler" class="widget-scheduler dhx_cal_container">
                                <div class="dhx_cal_navline">
                                    <div class="dhx_cal_prev_button">&nbsp;</div>
                                    <div class="dhx_cal_next_button">&nbsp;</div>
                                    <div class="dhx_cal_today_button"></div>
                                    <div class="dhx_cal_date"></div>
                                    <div class="dhx_cal_tab" name="day_tab"></div>
                                    <div class="dhx_cal_tab" name="week_tab"></div>
                                    <div class="dhx_cal_tab" name="month_tab"></div>
                                </div>
                                <div class="dhx_cal_header"></div>
                                <div class="dhx_cal_data"></div>
                            </div>
                            <div id="widgetFAQ" class="widget-faq">

                            </div>
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

        const widgetSchedulerContainer = document.getElementById("widgetScheduler");
        const widgetFAQContainer = document.getElementById("widgetFAQ");
        scheduler.init('widgetScheduler', new Date(), "month");
        scheduler.config.lightbox.sections=[
            {name:"title", height:72, map_to:"title", type:"textarea" , focus:true},
            {name:"description", height:200, map_to:"description", type:"textarea"},
            {name:"type", height: 40, type:"template", map_to:"type"},
            {name:"date", height:72, type:"time", map_to:"auto"}
        ];
        scheduler.locale.labels.section_title = 'Заголовок';
        scheduler.locale.labels.section_description = 'Описание';
        scheduler.locale.labels.section_type = 'Тип';
        scheduler.locale.labels.section_date = 'Дата и время';


        let events = [];
        fetch("/notes", {method: "GET"})
            .then(response => response.json())
            .then(resp => resp.map(el => (
                {
                    id: el.id,
                    title: el.title,
                    text: el.title,
                    description: el.description,
                    type: 'note',
                    start_date: el.start_date,
                    end_date: el.end_date,
                    backgroundColor: '#6cace4'
                })))
            .then(res => scheduler.parse(res));
        fetch("/tasks", {method: "GET"})
            .then(response => response.json())
            .then(resp => resp.map(el => (
                {
                    id: el.id + 'task',
                    title: el.title,
                    text: el.title,
                    description: el.description,
                    type: 'task',
                    start_date: el.start_date,
                    end_date: el.end_date,
                    backgroundColor: '#e52b50',
                    textColor: 'red'
                })))
            .then(res => scheduler.parse(res));
        scheduler.config.resize_month_events = true;
        scheduler.config.resize_month_timed= true;
        scheduler.attachEvent('onEventAdded', function(id, event) {
            if (event.type === "task") return false;
            event.text = event.title;
            console.log(event);
            createNote(event)
                .then(function(result){
                    scheduler.changeEventId(id, result.databaseId);
                });
        });

        scheduler.attachEvent('onEventChanged', function(id, event) {
            if (event.type === "task") return false;
            event.text = event.title;
            updateNote(event);
        });

        scheduler.attachEvent('onEventDeleted', function(id, event) {
            if (event.type === "task") return false;
            deleteNote(id);
        });
        scheduler.attachEvent("onEventDrag", function (id, mode, event){
            return false;
        });

        const widgetFAQ = webix.ui({
            container: "widgetFAQ",
            view: "tree",
            template: function(obj, common){
                if (obj.url !== undefined)
                    return common.icon(obj,common)
                        + common.folder(obj,common) + "<a href=\""+obj.url+"\">" + obj.value + "</a>";
                if (obj.$level > 2) {
                    return common.icon(obj,common)
                        + common.folder(obj,common) + "<i>" + obj.value + "</i>";
                } else {
                    return common.icon(obj,common)
                        + common.folder(obj,common) + obj.value;
                }
            },
            data: []
        });
        fetch("/articles", {
            method: "GET"
        })
            .then(result => result.json())
            .then(result => {
                let data = [];
                let parents = result.filter(elem => elem.parent == null);
                let children = result.filter(elem => elem.parent != null);
                data = parents.map(elem => ({id: elem.id, value: elem.title, data: []}));
                for (let i = 0; i < data.length; i++) {
                    data[i].data = children
                        .filter(elem => elem.parent === data[i].id)
                        .map(elem => ({
                            id: elem.id,
                            value: elem.title,
                            url: "/articles/"+elem.id,
                            data: [
                                {id: elem.id+"0", value: elem.content}
                            ]}
                        ))
                }
                widgetFAQ.parse(data);
            });

             async function updateNote(note) {
            return await fetch("/notes/" + note.id,
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
            return await fetch("/notes/",
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
            return await fetch("/notes/" +id,
                {
                    method: "DELETE",
                    headers: {
                        '${_csrf.headerName}': '${_csrf.token}'
                    }
                });
        }
    </script>
</#macro>


