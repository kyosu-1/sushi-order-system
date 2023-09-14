SHELL=/bin/bash

DC = docker-compose
MIGRATE_OPT_DEV ?= -env=development -config ./db/migrations/dbconfig.yaml


.PHONY: help
help: ## Display this help screen
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'

.PHONY: install
install: ## Install sql-migrate tool
	@echo "Installing sql-migrate..."
	@go install github.com/rubenv/sql-migrate/...@latest

.PHONY: migrate-up
migrate-up: ## Apply all up migrations
	@echo "Applying up migrations..."
	@sql-migrate up

.PHONY: migrate-down
migrate-down: ## Apply all down migrations
	@echo "Applying down migrations..."
	@sql-migrate down

.PHONY: migrate-status
migrate-status: ## Show migration status
	@echo "Showing migration status..."
	@sql-migrate status

.PHONY: create-migration
create-migration: ## Create a new migration. Use name=<name_of_migration>
	@echo "Creating a new migration..."
ifndef name
	$(error name is undefined. Usage: make create-migration name=<name_of_migration>)
endif
	@touch db/migrations/`date +"%Y%m%d%H%M%S"`_$(name).sql

.PHONY: up
up: ## Start all services (background)
	@echo "Starting up services..."
	$(DC) up -d

.PHONY: down
down: ## Stop all services
	@echo "Stopping services..."
	$(DC) down -v
