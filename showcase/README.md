# Advanced Expression Folding - Feature Showcase

Interactive showcase of all 40+ folding options for the Advanced Expression Folding IntelliJ plugin.

## Quick Start

1. **Open in browser**: Double-click `index.html` or serve locally
2. **Browse features**: Use search or category filters
3. **View examples**: Click feature card, switch to "Code Example" tab
4. **See comparisons**: Original and folded code side-by-side

## Files

- `index.html` - Main page with semantic HTML5
- `assets/css/styles.css` - Responsive design with dark mode
- `assets/js/app.js` - Interactive accordion, search, filters, tabs
- `assets/js/features-data.js` - 40+ features database with GitHub URLs

## Features

- **40+ folding options** organized by 16 categories
- **Real-time search** with filtering
- **Category filters** for quick browsing
- **Tab navigation** (Description & Code Examples)
- **Side-by-side code** comparison using iframes
- **GitHub integration** - all code from official repository
- **Responsive design** - desktop, tablet, mobile
- **Dark mode** - automatic theme detection
- **Accessibility** - WCAG 2.1 AA compliant
- **No dependencies** - pure HTML, CSS, JavaScript

## Browser Support

- Chrome/Edge 90+
- Firefox 88+
- Safari 14+
- Opera 76+

## Deployment

Copy `showcase/` folder to any static hosting:
- GitHub Pages (in `docs/` folder)
- Netlify (drag and drop)
- Vercel (GitHub integration)
- Any web server

## Customization

Edit CSS variables in `assets/css/styles.css`:

```css
:root {
    --primary: #2563eb;        /* Main color */
    --accent: #7c3aed;         /* Accent color */
    --bg-primary: #ffffff;     /* Background */
    /* ... */
}
```

## Adding Features

1. Add feature to `FEATURES` array in `assets/js/features-data.js`
2. Ensure example and folded files exist in GitHub repo
3. Page auto-updates

## Support

- GitHub: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding
- Issues: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/issues
- Install: JetBrains Marketplace
