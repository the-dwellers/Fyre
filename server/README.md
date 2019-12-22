# Fyre Development Server

This folder contains the files for the Fyre Development Server utility.

This Python script provides an interactive UI for managing a development server and also provides some features for the plugin during development.

## Interface
>Interactive terminal interface with an option to key binding.

- [x] Status banner
  - [x] Title
  - [x] Add status
    - [x] Color text

- [x] Menu
  - [x] Print possible options
  - [x] Listen for keys
    - [x] Perform action

## Server
>The actual development server instance that should be running on its
thread to be non-blocking.

- [ ] Start
  - [ ] Check for PaperMC updates
    - [ ] Download PaperMC
  - [ ] Check for ProtocolLib updates
    - [ ] Download ProtocolLib
  - [ ] Check for Vault updates
    - [ ] Download Vault
  - [ ] Start server

- [ ] Stop
  - [ ] Send `stop` to the server

- [ ] General
  - [ ] Install plugin
    - [ ] Build plugin
    - [ ] Reload the plugin
  - [ ] Install data pack
    - [ ] Reload the data pack

- [ ] Attach terminal I/O to the server

## Socket
>Simple HTTP API to listen for requests from the plugin that allows
management while in-game should also be on its thread to be
non-blocking.

- [ ] Listen for connections
  - [ ] Perform proper action on the server
