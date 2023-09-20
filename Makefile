SHELL=/bin/bash

DC = docker-compose
MIGRATE_OPT_DEV ?= -env development -config db/migrations/config/dbconfig.yaml

# Include .env file
include .env

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
	@set -a; source .env; set +a; sql-migrate up $(MIGRATE_OPT_DEV)

.PHONY: migrate-down
migrate-down: ## Apply all down migrations
	@echo "Applying down migrations..."
	@set -a; source .env; set +a; sql-migrate down $(MIGRATE_OPT_DEV)

.PHONY: migrate-status
migrate-status: ## Show migration status
	@echo "Showing migration status..."
	set -a; source .env; set +a; sql-migrate status $(MIGRATE_OPT_DEV)

.PHONY: create-migration
create-migration: ## Create a new migration. Use name=<name_of_migration>
	@echo "Creating a new migration..."
ifndef name
	$(error name is undefined. Usage: make create-migration name=<name_of_migration>)
endif
	@set -a; source .env; set +a; sql-migrate new $(name) $(MIGRATE_OPT_DEV)

.PHONY: up
up: ## Start all services (background)
	@echo "Starting up services..."
	$(DC) up -d

.PHONY: down
down: ## Stop all services
	@echo "Stopping services..."
	$(DC) down -v

.PHONY: logs
logs: ## Show logs
	@echo "Showing logs..."
	$(DC) logs -f

.PHONY: seed
seed: ## Seed the database
	@echo "Seeding the database..."
	@mysql -u $(DB_USER) -p$(DB_PASSWORD) -h $(DB_HOST) -P $(DB_PORT) --protocol=tcp $(DB_NAME) < db/test/seed.sql
	@echo "Done seeding."

.PHONY: cleanup
cleanup: ## delete all db data
	@echo "Deleting all db data..."
	@mysql -u $(DB_USER) -p$(DB_PASSWORD) -h $(DB_HOST) -P $(DB_PORT) --protocol=tcp $(DB_NAME) < db/test/cleanup.sql
	@echo "Done deleting db data."
