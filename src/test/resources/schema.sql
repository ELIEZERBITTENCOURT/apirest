CREATE TABLE IF NOT EXISTS `companies` (
    `id` BINARY(16) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `website` VARCHAR(255) NOT NULL,
    `description` TEXT NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,

    `id_text` VARCHAR(36) GENERATED ALWAYS AS
    (INSERT(
        INSERT(
        INSERT(
            INSERT(hex(`id`),9,0,'-'),
            14,0,'-'),
        19,0,'-'),
        24,0,'-')
    ) VIRTUAL,

    CONSTRAINT `companies_pk` PRIMARY KEY (`id`),
    CONSTRAINT `companies_name_unique` UNIQUE (`name`),
    CONSTRAINT `companies_email_unique` UNIQUE (`email`)
);

CREATE TABLE IF NOT EXISTS `skills` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,

    CONSTRAINT `skills_pk` PRIMARY KEY (`id`),
    CONSTRAINT `skills_name_unique` UNIQUE (`name`)
);

CREATE TABLE IF NOT EXISTS `candidates` (
    `id` BINARY(16) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `description` TEXT NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `linkedin` VARCHAR(255) NOT NULL,
    `github` VARCHAR(255) NOT NULL,

    `id_text` VARCHAR(36) GENERATED ALWAYS AS
    (INSERT(
        INSERT(
        INSERT(
            INSERT(hex(`id`),9,0,'-'),
            14,0,'-'),
        19,0,'-'),
        24,0,'-')
    ) VIRTUAL,

    CONSTRAINT `candidates_pk` PRIMARY KEY (`id`),
    CONSTRAINT `candidates_email_unique` UNIQUE (`email`),
    CONSTRAINT `candidates_linkedin_unique` UNIQUE (`linkedin`),
    CONSTRAINT `candidates_github_unique` UNIQUE (`github`)
);

CREATE TABLE IF NOT EXISTS `jobs` (
    `id` binary(16) NOT NULL,
    `name` varchar(255) NOT NULL,
    `description` TEXT NOT NULL,
    `level` enum('JUNIOR', 'MID_LEVEL', 'SENIOR') NOT NULL,
    `location` varchar(255) NOT NULL,
    `salary` decimal(38, 2) DEFAULT NULL,
    `type` enum(
      'FREELANCE',
      'FULL_TIME',
      'INTERSHIP',
      'PART_TIME',
      'TEMPORARY'
    ) NOT NULL,
    `company_id` binary(16) NOT NULL,

    `id_text` VARCHAR(36) GENERATED ALWAYS AS
    (INSERT(
        INSERT(
        INSERT(
            INSERT(hex(`id`),9,0,'-'),
            14,0,'-'),
        19,0,'-'),
        24,0,'-')
    ) VIRTUAL,
    `comapany_id_text` VARCHAR(36) GENERATED ALWAYS AS
    (INSERT(
        INSERT(
        INSERT(
            INSERT(hex(`company_id`),9,0,'-'),
            14,0,'-'),
        19,0,'-'),
        24,0,'-')
    ) VIRTUAL,
    
    CONSTRAINT `jobs_pk` PRIMARY KEY (`id`),
    CONSTRAINT `jobs_company_id_fk` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`)  
);

CREATE TABLE IF NOT EXISTS `jobs_skills` (
    `job_id` binary(16) NOT NULL,
    `skills_id` bigint NOT NULL,

    `job_id_text` VARCHAR(36) GENERATED ALWAYS AS
    (INSERT(
        INSERT(
        INSERT(
            INSERT(hex(`job_id`),9,0,'-'),
            14,0,'-'),
        19,0,'-'),
        24,0,'-')
    ) VIRTUAL,
    
    CONSTRAINT `jobs_skills_pk` PRIMARY KEY (`job_id`, `skills_id`),
    CONSTRAINT `jobs_skills_job_id_fk` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`id`),
    CONSTRAINT `jobs_skills_skills_id_fk` FOREIGN KEY (`skills_id`) REFERENCES `skills` (`id`)
);

CREATE TABLE IF NOT EXISTS `jobs_candidates` (
    `job_id` binary(16) NOT NULL,
    `candidates_id` binary(16) NOT NULL,

    `job_id_text` VARCHAR(36) GENERATED ALWAYS AS
    (INSERT(
        INSERT(
        INSERT(
            INSERT(hex(`job_id`),9,0,'-'),
            14,0,'-'),
        19,0,'-'),
        24,0,'-')
    ) VIRTUAL,
    `candidates_id_text` VARCHAR(36) GENERATED ALWAYS AS
    (INSERT(
        INSERT(
        INSERT(
            INSERT(hex(`job_id`),9,0,'-'),
            14,0,'-'),
        19,0,'-'),
        24,0,'-')
    ) VIRTUAL,
    
    CONSTRAINT `jobs_candidates_pk` PRIMARY KEY (`job_id`, `candidates_id`),
    CONSTRAINT `jobs_candidates_fk_job_id` FOREIGN KEY (`job_id`) REFERENCES `jobs`(`id`),
    CONSTRAINT `jobs_candidates_fk_candidates_id` FOREIGN KEY (`candidates_id`) REFERENCES `candidates`(`id`)
);