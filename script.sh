git filter-branch --env-filter '
if [ "$GIT_AUTHOR_NAME" = "agond" ]
then
    export GIT_AUTHOR_NAME="akashgond3112"
    export GIT_AUTHOR_EMAIL="akashgond3112@gmail.com"
    export GIT_COMMITTER_NAME="akashgond3112"
    export GIT_COMMITTER_EMAIL="akashgond3112@gmail.com"
fi
' HEAD