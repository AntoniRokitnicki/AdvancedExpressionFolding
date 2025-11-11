class FeatureShowcase {
    constructor() {
        this.features = FEATURES;
        this.filteredFeatures = FEATURES;
        this.currentFilter = 'all';
        this.init();
    }

    init() {
        this.setupEventListeners();
        this.initializeCategories();
        this.renderFeatures();
    }

    setupEventListeners() {
        const searchInput = document.getElementById('searchInput');
        searchInput.addEventListener('input', (e) => this.handleSearch(e));
        searchInput.addEventListener('keydown', (e) => {
            if (e.key === 'Escape') searchInput.blur();
        });

        document.addEventListener('click', (e) => {
            if (e.target.classList.contains('filter-btn')) this.handleCategoryFilter(e);
            if (e.target.classList.contains('accordion-header')) this.handleAccordionToggle(e);
            if (e.target.classList.contains('tab-btn')) this.handleTabSwitch(e);
        });
    }

    initializeCategories() {
        const categories = [...new Set(this.features.map(f => f.category))].sort();
        const filterContainer = document.querySelector('.category-filters');
        categories.forEach(category => {
            const btn = document.createElement('button');
            btn.className = 'filter-btn';
            btn.textContent = category;
            btn.dataset.category = category;
            filterContainer.appendChild(btn);
        });
    }

    handleCategoryFilter(e) {
        document.querySelectorAll('.filter-btn').forEach(btn => btn.classList.remove('active'));
        e.target.classList.add('active');
        this.currentFilter = e.target.dataset.category;
        this.applyFilters();
    }

    handleSearch(e) {
        const searchTerm = e.target.value.toLowerCase();
        this.filteredFeatures = this.features.filter(feature => {
            const matchesSearch = feature.name.toLowerCase().includes(searchTerm) ||
                                feature.description.toLowerCase().includes(searchTerm);
            const matchesCategory = this.currentFilter === 'all' || feature.category === this.currentFilter;
            return matchesSearch && matchesCategory;
        });
        this.renderFeatures();
    }

    applyFilters() {
        this.filteredFeatures = this.currentFilter === 'all' ?
            this.features :
            this.features.filter(f => f.category === this.currentFilter);
        this.renderFeatures();
    }

    handleAccordionToggle(e) {
        const accordion = e.currentTarget;
        const content = accordion.nextElementSibling;
        const isExpanded = accordion.getAttribute('aria-expanded') === 'true';
        accordion.setAttribute('aria-expanded', !isExpanded);
        content.classList.toggle('open');
    }

    handleTabSwitch(e) {
        const tabBtn = e.target;
        const container = tabBtn.closest('.tabs-container');
        container.querySelectorAll('.tab-btn').forEach(btn => btn.classList.remove('active'));
        container.querySelectorAll('.tab-content').forEach(content => content.classList.remove('active'));
        tabBtn.classList.add('active');
        const activeContent = container.querySelector(`.tab-content[data-tab="${tabBtn.dataset.tab}"]`);
        if (activeContent) activeContent.classList.add('active');
    }

    renderFeatures() {
        const container = document.getElementById('accordionContainer');
        container.innerHTML = '';

        if (this.filteredFeatures.length === 0) {
            container.innerHTML = '<div class="no-results">No features found.</div>';
            return;
        }

        const grouped = this.groupByCategory(this.filteredFeatures);
        Object.entries(grouped).forEach(([category, features]) => {
            container.appendChild(this.createAccordionSection(category, features));
        });
    }

    groupByCategory(features) {
        return features.reduce((groups, feature) => {
            if (!groups[feature.category]) groups[feature.category] = [];
            groups[feature.category].push(feature);
            return groups;
        }, {});
    }

    createAccordionSection(category, features) {
        const accordion = document.createElement('div');
        accordion.className = 'accordion-category';

        const header = document.createElement('button');
        header.className = 'accordion-header';
        header.setAttribute('aria-expanded', 'false');
        header.innerHTML = `
            <span class="accordion-icon">▶</span>
            <span class="accordion-title">${category}</span>
            <span class="accordion-count">${features.length}</span>
        `;

        const content = document.createElement('div');
        content.className = 'accordion-content';
        const grid = document.createElement('div');
        grid.className = 'features-grid';

        features.forEach(feature => {
            grid.appendChild(this.createFeatureCard(feature));
        });

        content.appendChild(grid);
        accordion.appendChild(header);
        accordion.appendChild(content);
        return accordion;
    }

    createFeatureCard(feature) {
        const card = document.createElement('div');
        card.className = 'feature-card';

        card.innerHTML = `
            <div class="card-header">
                <h3>${feature.name}</h3>
                <span class="category-badge">${feature.category}</span>
            </div>
            <div class="tabs-container">
                <div class="tabs-buttons">
                    <button class="tab-btn active" data-tab="description">Description</button>
                    <button class="tab-btn" data-tab="code">Code Example</button>
                </div>
                <div class="tab-content active" data-tab="description">
                    <p>${feature.description}</p>
                    <div class="external-links">
                        <a href="${getGithubLink(feature.exampleFile, 'example')}" target="_blank" rel="noopener" class="link-button">
                            View Example
                        </a>
                        <a href="${getGithubLink(feature.foldedFile, 'folded')}" target="_blank" rel="noopener" class="link-button">
                            View Folded
                        </a>
                    </div>
                </div>
                <div class="tab-content" data-tab="code">
                    <div class="code-comparison">
                        <div class="code-pane">
                            <div class="code-header">Original</div>
                            <iframe class="code-iframe" src="${getExampleUrl(feature.exampleFile)}" title="Original" loading="lazy"></iframe>
                        </div>
                        <div class="code-pane">
                            <div class="code-header">Folded</div>
                            <iframe class="code-iframe" src="${getFoldedUrl(feature.foldedFile)}" title="Folded" loading="lazy"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        `;
        return card;
    }
}

document.addEventListener('DOMContentLoaded', () => new FeatureShowcase());
