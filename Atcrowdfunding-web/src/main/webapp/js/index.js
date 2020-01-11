$(function () {
    $("#index-user-htm").click(goUserPage);
    $("#index-member-htm").click(goMemberPage);
});

function goUserPage() {
    location.href = "/main.htm?t=" + Math.random();
}

function goMemberPage() {
    location.href = "/member.htm?t=" + Math.random();
}
