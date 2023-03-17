const data = {
    labels: [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "asd",
        "Juasdne",
        "Junasde",
        "hdfgh",
        "Junfghdfe",
        "fdsg",
        "xcasdv",
        "Jucvbxcne",
        "xcv",
        "Juxcbxcne",
        "Junxcve",
        "Juhjkne",
        "cvb",
        "Jughne",
        "Juncvbe",
        "Juncvbce",
        "rhjkhjete",
        "pdsd",
        "hjk",
    ],
    datasets: [
        {
            label: "Buspass",
            backgroundColor: "#8ed7a7",
            borderColor: "#8ed7a7",
            borderWidth: 1,
            data: [
                10, 20, 30, 40, 50, 60, 20, 30, 40, 50, 6, 60, 20, 30, 40, 50,
                6, 60, 20, 30, 40, 50, 6, 6, 100,
            ],
        },
    ],
};
const ctx = document.getElementById("route-bar-chart").getContext("2d");
const routeBarChart = new Chart(ctx, {
    type: "bar",
    data: data,
    options: {
        responsive: true,
        height: "500px",
        scales: {
            yAxes: [
                {
                    ticks: {
                        beginAtZero: true,
                    },
                },
            ],
        },
    },
});

const data1 = {
    labels: [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "asd",
        "Juasdne",
        "Junasde",
        "hdfgh",
        "Junfghdfe",
        "fdsg",
        "xcasdv",
        "Jucvbxcne",
        "xcv",
        "Juxcbxcne",
        "Junxcve",
        "Juhjkne",
        "cvb",
        "Jughne",
        "Juncvbe",
        "Juncvbce",
        "rhjkhjete",
        "pdsd",
        "hjk",
    ],
    datasets: [
        {
            label: "Buspass",
            backgroundColor: "#8ed7a7",
            borderColor: "#8ed7a7",
            borderWidth: 1,
            data: [
                10, 20, 30, 40, 50, 60, 20, 30, 40, 50, 6, 60, 20, 30, 40, 50,
                6, 60, 20, 30, 40, 50, 6, 6, 100,
            ],
        },
    ],
};
const ctx1 = document.getElementById("department-bar-chart").getContext("2d");
const departmentBarChart = new Chart(ctx1, {
    type: "bar",
    data: data1,
    options: {
        responsive: true,
        height: "500px",
        scales: {
            yAxes: [
                {
                    ticks: {
                        beginAtZero: true,
                    },
                },
            ],
        },
    },
});
