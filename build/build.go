package main

import (
	"os"
	"os/exec"
	"path/filepath"
)

func main() {
	ex, err := os.Executable()
	if err != nil {
		panic(err)
	}
	exPath := filepath.Dir(ex)
	command := exec.Command("java", "-jar", exPath+"/jar/crescent_town.jar")
	command.Stdout = os.Stdout
	command.Stderr = os.Stderr
	command.Dir = exPath
	command.Start()
}
