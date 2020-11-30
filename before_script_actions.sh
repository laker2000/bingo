wget -c https://golang.org/dl/go1.15.5.linux-amd64.tar.gz

sudo tar -C . -xvzf go1.15.5.linux-amd64.tar.gz

export  PATH=$PATH:/usr/local/go/bin
ls -ltra ~/
go version
go get github.com/darklynx/request-baskets
#export PATH=$PATH:$GOPATH/bin

ls ~/go/bin/./request-baskets
chmod 777 ~/go/bin/./request-baskets
~/go/bin/./request-baskets 2>&1 | grep token | sed 's/^.*: //' | tee kkk.txt &