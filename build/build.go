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
	command.Dir = exPath
	command.Stderr = os.Stderr
	command.Stdout = os.Stdout
	command.Start()
}
